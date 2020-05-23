package by.epam.cafe.dao.pool;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPoolTest {

    @Test(description = "Testing connectionPool for ability to process a lot of threads" +
            "simultaneously", timeOut = 100000)
    public void general() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        BlockingDeque<Connection> deque = new LinkedBlockingDeque<>();
        for (int i = 0; i < ConnectionPool.MAX_COUNT * 2; i++) {
            Thread tr1 = new Thread(new ConnectionPutter(deque));
            tr1.start();
            threads.add(tr1);
        }

        for (int i = 0; i < ConnectionPool.MAX_COUNT * 2; i++) {
            Thread tr2 = new Thread(new ConnectionReleaser(deque));
            tr2.start();
            threads.add(tr2);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Executed successfully");
    }
}

class ConnectionPutter implements Runnable {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    BlockingDeque<Connection> deque;

    public ConnectionPutter(BlockingDeque<Connection> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        Connection takeConnection = connectionPool.takeConnection();
        System.out.println("takeConnection = " + takeConnection);
        deque.add(takeConnection);
    }
}

class ConnectionReleaser implements Runnable {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    BlockingDeque<Connection> deque;

    public ConnectionReleaser(BlockingDeque<Connection> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        try {
            Connection take = deque.take();
            System.out.println("take = " + take);
            connectionPool.release(take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}