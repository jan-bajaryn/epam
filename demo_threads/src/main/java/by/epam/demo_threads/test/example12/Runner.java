package by.epam.demo_threads.example12;

import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    public static void main(String[] args) {

        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

}

