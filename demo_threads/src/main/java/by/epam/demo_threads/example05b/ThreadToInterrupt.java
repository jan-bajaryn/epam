package by.epam.demo_threads.example05b;

import java.util.concurrent.TimeUnit;

public class ThreadToInterrupt extends Thread {

    public static void main(String[] args) {
        ThreadToInterrupt threadToInterrupt = new ThreadToInterrupt("abc");
        threadToInterrupt.start();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadToInterrupt.interrupt();

    }

    public ThreadToInterrupt(String name) {
        super(name);

    }

    public void run() {
        System.out.printf("Поток %s начал работу... \n", Thread.currentThread().getName());

        while (!Thread.interrupted()) {
            System.out.printf("Поток %s работает... \n", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                //e.printStackTrace();
                //throw new RuntimeException(e);

            }
        }
        System.out.printf("Поток %s завершил работу... \n", Thread.currentThread().getName());

    }
}
