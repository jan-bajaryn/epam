package by.epam.task12.service.filler.thread;

import by.epam.task12.entity.impl.MatrixAtomicImpl;
import by.epam.task12.service.CommonCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownFiller extends Thread {

    private static final Logger log = LogManager.getLogger(CountDownFiller.class);

    private int value;
    private int beginPosition;
    private MatrixAtomicImpl matrix;
    private CountDownLatch countDownLatch;

    public CountDownFiller(int value, int beginPosition, MatrixAtomicImpl matrix, CountDownLatch countDownLatch) {
        super(Integer.toString(value));
        this.value = value;
        this.beginPosition = beginPosition;
        this.matrix = matrix;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        int temp = beginPosition;
        int size = Math.min(matrix.calcColumns(), matrix.calcRows());
        while (true) {
            AtomicInteger element = matrix.getElement(temp, temp);
            temp = (temp + 1) % size;
            boolean b = element.compareAndSet(0, value);
            if (b) {
                countDownLatch.countDown();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.info("Interrupted.");
            }

        }
    }
}

