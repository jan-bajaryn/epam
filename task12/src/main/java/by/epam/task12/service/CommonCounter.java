package by.epam.task12.service;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CommonCounter {
    private int changed;
    private int expected;

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public CommonCounter(int expected) {
        this.expected = expected;
    }

    public void changeSuccess() {
        lock.lock();
        try {
            ++changed;
        } finally {
            condition.signalAll();
            lock.unlock();
        }
    }

    public boolean isExit() {
        lock.lock();
        try {
            return changed == expected;
        } finally {
            condition.signalAll();
            lock.unlock();
        }
    }

}
