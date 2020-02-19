package by.epam.demo_threads.example12;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Store {
    private int product = 0;
    private ReentrantLock locker;
    private Condition condition;

    public Store(ReentrantLock locker, Condition condition) {
        this.locker = locker;
        this.condition = condition;
    }

    public synchronized void put(Producer producer) {
        locker.lock();
        try {
            while (product >= 5) {
                condition.await();
            }
            product++;
            System.out.println("Производитель " + producer.calcName() + " добавил 1 товар");
            System.out.println("Товаров на складе: " + product);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();

        }

    }

    public synchronized void get(Consumer consumer) {

        locker.lock();
        try {
            while (product < 1) {
                condition.await();
            }
            product--;
            System.out.println("Покупатель " + consumer.calcName() + " купил 1 товар");
            System.out.println("Товаров на складе: " + product);
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }

    }

}

