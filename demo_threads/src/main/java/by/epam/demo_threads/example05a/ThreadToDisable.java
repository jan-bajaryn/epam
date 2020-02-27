package by.epam.demo_threads.example05a;


import java.util.concurrent.TimeUnit;

public class ThreadToDisable implements Runnable {

    private boolean isActive;

    void disable() {
        isActive = false;
    }

    ThreadToDisable() {
        isActive = true;
    }

    public void run() {

        System.out.printf("Поток %s начал работу... \n", Thread.currentThread().getName());
        int counter = 1; // счетчик циклов
        while (isActive) {
            System.out.println("Цикл " + counter++);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Поток прерван");
            }
        }
        System.out.printf("Поток %s завершил работу... \n", Thread.currentThread().getName());
    }
}

