package by.epam.task12.service.filler;

import by.epam.task12.entity.impl.MatrixImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RangeDiagonalFiller extends Thread {

    private static Logger log = LogManager.getLogger(RangeDiagonalFiller.class);

    private MatrixImpl matrixImpl;
    int minIndex;
    int maxIndex;
    int input;
    private RangeDiagonalFiller nextFiller;

    public RangeDiagonalFiller(MatrixImpl matrixImpl, int minIndex, int maxIndex, int input) {
        this.matrixImpl = matrixImpl;
        this.minIndex = minIndex;
        this.maxIndex = maxIndex;
        this.input = input;
    }

    public RangeDiagonalFiller(RangeDiagonalFiller thread, int input) {
        this.matrixImpl = thread.matrixImpl;
        this.minIndex = thread.minIndex;
        this.maxIndex = thread.maxIndex;
        this.input = input;
        this.nextFiller = thread.nextFiller;
    }

    public void setNextFiller(RangeDiagonalFiller nextFiller) {
        this.nextFiller = nextFiller;
    }

    @Override
    public void run() {
        try {
            executeFill();
        } catch (InterruptedException e) {
            log.info("Thread was interrupted on the join.");

        }
    }

    private void executeFill() throws InterruptedException {
        fill();
        fillNextIfInterrupted();
    }

    private void fillNextIfInterrupted() throws InterruptedException {
        if (nextFiller == null) {
            return;
        }
        nextFiller.join();
        if (nextFiller.isInterrupted()) {
            nextFiller.executeFill();
        }
    }

    private void fill() {
        log.info("minIndex = {}, maxIndex = {}", minIndex, maxIndex);
        for (int i = minIndex; i <= maxIndex; i++) {
            log.info("i = {}", i);
            if (matrixImpl.calcValue(i, i) == 0) {
                matrixImpl.setValue(i, i, input);
            }
        }
    }
}
