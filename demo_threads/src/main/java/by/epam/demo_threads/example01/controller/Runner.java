package by.epam.demo_threads.example01.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) {
        MyThread myThread = new MyThread("my name");
        myThread.setPriority(10);
        myThread.start();

//        Thread.currentThread().setPriority(1);
//        System.out.println(Thread.currentThread().getPriority());
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("dgb");
    }
}

class MyThread extends Thread {
    private static final Logger log = LogManager.getLogger(MyThread.class);

    public MyThread(String name) {
        super(name);
    }




    @Override
    public void run() {
        System.out.println("Hello world");
        log.info("I'm running.");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread());
    }
}