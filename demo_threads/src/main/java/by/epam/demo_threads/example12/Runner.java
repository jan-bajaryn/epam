package by.epam.demo_threads.example12;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    public static void main(String[] args) {
        ReentrantLock locker = new ReentrantLock();
        Condition condition = locker.newCondition();

        Store store = new Store(locker, condition);
        int countProducts = 20;
        for (int i = 0; i < 5; i++) {
            new Producer(store, countProducts, "Producer " + i).start();
            new Consumer(store, countProducts, "Consumer " + i).start();
        }


    }


}
