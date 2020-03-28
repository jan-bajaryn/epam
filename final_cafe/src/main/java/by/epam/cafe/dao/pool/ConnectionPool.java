package by.epam.cafe.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
    private static final String URL = "jdbc:mysql://localhost:3306/pizzeria";
    private static Properties properties = new Properties();

    private List<Connection> passive = new ArrayList<>();
    private List<Connection> active = new ArrayList<>();

    private static final String RESOURCE = "property/database.properties";

    private ReentrantLock locker = new ReentrantLock();
    private Condition condition = locker.newCondition();

    static {
//        FileInputStream fis;
//        try {
//            InputStream inputStream = ConnectionPool.class
//                    .getClassLoader().getResourceAsStream(RESOURCE);
//            properties.load(Objects.requireNonNull(inputStream));
//        } catch (IOException | NullPointerException e) {
//            throw new RuntimeException("Error in initialization properties file.", e);
//        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        properties.put("user", "root");
        properties.put("password", "pass");
        properties.put("autoReconnect", "true");
        properties.put("characterEncoding", "UTF-8");
        properties.put("useUnicode", "true");
        properties.put("serverTimezone", "UTC");
        properties.put("useSSL", "false");

    }

    public Connection takeConnection() {
        try {
            return DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            log.error("Exception in takeConnection: ", e);
            return null;
        }
//        locker.lock();
//        try {
//            Connection cn = null;
//            while (cn == null) {
//                try {
//                    cn = createOrAccess();
//                    if (cn != null) {
//                        active.add(cn);
//                        return cn;
//                    }
//                } catch (SQLException e) {
//                    log.debug("Can't connect to the database with so properties");
//                }
//                try {
//                    condition.await();
//                } catch (InterruptedException e) {
//                    log.debug("Condition was interrupted");
//                }
//            }
//        } finally {
//            condition.notifyAll();
//            locker.unlock();
//        }
//        return null;
    }

    public void release(Connection cn) {
        try {
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection createOrAccess() throws SQLException {
        locker.lock();
        try {
            if (!passive.isEmpty()) {
                return passive.get(0);
            }

            if (passive.size() + active.size() >= MAX_COUNT) {
                return null;
            }
            return DriverManager.getConnection(URL, properties);
        } finally {
            condition.notifyAll();
            locker.unlock();
        }
    }
}
