package by.epam.demo_threads.example05c;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Daemons {

    public static void main(String[] args) {
        DaemonThread daemon = new DaemonThread(null, "Daemon");
        daemon.setDaemon(true);

        DaemonThread regular = new DaemonThread(daemon, "Regular");
        regular.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        regular.interrupt();
    }
}

class DaemonThread extends Thread {
    private static final Logger log = LogManager.getLogger(Daemons.class);

    private Thread next;

    public DaemonThread(Thread next, String name) {
        super(name);
        this.next = next;
    }

    @Override
    public void run() {
        if (next != null) {
            next.start();
        }
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.info("Interrupted");
            }
            System.out.println(getName() + " waiting...");
        }
    }
}