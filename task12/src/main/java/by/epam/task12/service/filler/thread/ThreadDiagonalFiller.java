package by.epam.task12.service.filler.thread;

import by.epam.task12.entity.impl.MatrixImpl;
import by.epam.task12.service.filler.RangeDiagonalFiller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadDiagonalFiller {

    private static final Logger log = LogManager.getLogger(ThreadDiagonalFiller.class);

    public void fill(MatrixImpl matrixImpl, int[] arr) {
        if (matrixImpl == null) {
            return;
        }

        int coresCount = arr.length;
        log.info("coresCount = {}", coresCount);
        int[] ints = decideSize(coresCount - 1, matrixImpl);
        log.info("ints = {},", ints);

        int counter = 0;
        RangeDiagonalFiller[] threads = new RangeDiagonalFiller[ints.length];
        for (int i = 0; i < ints.length; i++) {
            int nexCount = counter + ints[i];
            RangeDiagonalFiller thread = fillRange(matrixImpl, counter, nexCount - 1, arr[i + 1]);
            threads[i] = thread;
            counter = nexCount;
        }
        defineNextFillers(threads);
        startThreads(threads);
        joinThreads(threads);
        repairIfNeed(threads, matrixImpl, arr[0]);
    }

    private void repairIfNeed(RangeDiagonalFiller[] threads, MatrixImpl matrixImpl, int i) {
        if (threads[0].isInterrupted()) {
            new RangeDiagonalFiller(threads[0], i).start();
        }
    }

    private void defineNextFillers(RangeDiagonalFiller[] threads) {
        for (int i = 0; i < threads.length - 1; i++) {
            threads[i].setNextFiller(threads[i + 1]);
        }
//        threads[threads.length - 1].setNextFiller(threads[0]);
    }

    private void startThreads(Thread[] threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private void joinThreads(Thread[] threads) {
        try {
            threads[0].join();
        } catch (InterruptedException e) {
            log.info("join interrupted in joinThreads");
        }
    }

    private RangeDiagonalFiller fillRange(MatrixImpl matrixImpl, int minIndex, int maxIndex, int input) {
        return new RangeDiagonalFiller(matrixImpl, minIndex, maxIndex, input);
    }

    private int[] decideSize(int coresCount, MatrixImpl matrixImpl) {
        if (matrixImpl == null || coresCount <= 1) {
            return new int[0];
        }
        int min = Integer.min(matrixImpl.calcColumns(), matrixImpl.calcRows());

        if (min <= coresCount) {
            return lessThanThreads(min);
        }

        return moreThanThreads(min, coresCount);
    }

    private int[] moreThanThreads(int min, int maxThreadsUse) {
        int[] ints;
        int minInput = min / maxThreadsUse;
        int remain = min % maxThreadsUse;

        ints = new int[maxThreadsUse];

        for (int i = 0; i < maxThreadsUse; i++) {
            ints[i] = minInput;
        }
        for (int i = 0; i < remain; i++) {
            ++ints[i];
        }
        return ints;
    }

    private int[] lessThanThreads(int min) {
        int[] ints;
        ints = new int[min];
        for (int i = 0; i < min; i++) {
            ints[i] = 1;
        }
        return ints;
    }
}
