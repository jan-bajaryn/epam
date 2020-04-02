package by.epam.cafe.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger log = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    private ConnectionPool() {
    }

    public static final int MAX_COUNT = 100;
    private static Properties properties = new Properties();

    private List<Connection> passive = new ArrayList<>();

    private List<Connection> inUse = new ArrayList<>();

    private static final String RESOURCE = "/property/database.properties";

    private ReentrantLock locker = new ReentrantLock(true);
    private Condition condition = locker.newCondition();

    private Semaphore semaphore = new Semaphore(MAX_COUNT, true);

    static {
        init();
    }

    private static void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            InputStream is = ConnectionPool.class.getResourceAsStream(RESOURCE);
            properties.load(is);
        } catch (ClassNotFoundException | IOException e) {
            log.fatal("Problems in initialization ConnectionPool", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public Connection takeConnection() {
//        try {
//            String url = properties.getProperty("url");
//            log.info("url = {}", url);
//            return DriverManager.getConnection(url, properties);
//        } catch (SQLException e) {
//            log.error("Exception in takeConnection: ", e);
//            return null;
//        }
        try {
            semaphore.acquire();

            locker.lock();
            while (noAccessibleCon()) {
                log.info("No accessible connections now");
                condition.await();
            }
            return createOrAccess();

        } catch (InterruptedException | SQLException e) {
            log.error("e: ", e);
            return null;
        }finally {
            locker.unlock();
        }
    }

    boolean noAccessibleCon() {
        return passive.isEmpty() && (passive.size() + inUse.size() >= MAX_COUNT);
    }

    public void release(Connection cn) {
//        try {
//            cn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            locker.lock();
            if (cn != null) {
                inUse.remove(cn);
                passive.add(cn);

                condition.signal();
                semaphore.release();
            }
        } finally {
            locker.unlock();
        }
    }

    private Connection createOrAccess() throws SQLException {
        if (!passive.isEmpty()) {

            Connection remove = passive.remove(0);
            inUse.add(remove);

            return remove;
        }

//            if (passive.size() + inUse.size() >= MAX_COUNT) {
//                return null;
//            }
        return DriverManager.getConnection(properties.getProperty("url"), properties);
    }

    public void destroy() throws SQLException {
        for (Connection connection : inUse) {
            connection.close();
        }
        for (Connection connection : passive) {
            connection.close();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
        super.finalize();
    }
}
