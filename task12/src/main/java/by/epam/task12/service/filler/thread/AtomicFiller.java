package by.epam.task12.service.filler.thread;

import by.epam.task12.entity.impl.MatrixAtomicImpl;
import by.epam.task12.service.CommonCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicFiller extends Thread {

    private static final Logger log = LogManager.getLogger(AtomicFiller.class);

    private int value;
    private int beginPosition;
    private MatrixAtomicImpl matrix;
    private CommonCounter commonCounter;

    public AtomicFiller(int value, int beginPosition, MatrixAtomicImpl matrix, CommonCounter commonCounter) {
        super(Integer.toString(value));
        this.value = value;
        this.beginPosition = beginPosition;
        this.matrix = matrix;
        this.commonCounter = commonCounter;
    }

    @Override
    public void run() {
        int temp = beginPosition;
        log.info("Begin position = {}", beginPosition);
        int size = Math.min(matrix.calcColumns(), matrix.calcRows());
        while (!commonCounter.isExit() && !Thread.interrupted()) {
            AtomicInteger element = matrix.getElement(temp, temp);
            temp = (temp + 1) % size;
            boolean b = element.compareAndSet(0, value);
            if (b) {
                commonCounter.changeSuccess();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.info("Interrupted.");
                Thread.currentThread().interrupt();
            }

        }
    }
}
