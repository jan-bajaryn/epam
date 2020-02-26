package by.epam.task12.service.filler;


import by.epam.task12.entity.impl.MatrixAtomicImpl;
import by.epam.task12.service.filler.thread.CountDownFiller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class DiagonalFillerCountDown {

    private static final Logger log = LogManager.getLogger(DiagonalFillerCountDown.class);

    public void fill(MatrixAtomicImpl matrix, int[] arr) {

        int[] pos = decideBeginPositions(matrix, arr);
        log.info("pos = {}", pos);
        CountDownLatch countDownLatch = new CountDownLatch(Math.min(matrix.calcColumns(), matrix.calcRows()));
        Thread[] threads = createThreads(arr, matrix, pos, countDownLatch);
        startThreads(threads);
        interruptThreads(threads, countDownLatch);
    }

    private void interruptThreads(Thread[] threads, CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.info("main thread was interrupted.");
        }
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private void startThreads(Thread[] threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private Thread[] createThreads(int[] arr, MatrixAtomicImpl matrix, int[] pos, CountDownLatch countDownLatch) {
        Thread[] threads = new Thread[arr.length];
        int position = 0;
        for (int i = 0; i < arr.length; i++) {
            threads[i] = new CountDownFiller(arr[i], position, matrix, countDownLatch);
            position = position + pos[i];
        }
        return threads;
    }

    private int[] decideBeginPositions(MatrixAtomicImpl matrix, int[] arr) {
        int min = Integer.min(matrix.calcColumns(), matrix.calcRows());
        int[] retArr = new int[arr.length];
        if (arr.length >= min) {
            threadsMoreOrEqual(arr, min, retArr);
            return retArr;
        }
        threadLess(arr, min, retArr);
        return retArr;
    }

    private void threadLess(int[] arr, int min, int[] retArr) {
        int divide = min / arr.length;
        int remain = min % arr.length;
        decideCounts(retArr, divide, remain);
        transformToIndexed(arr, retArr);
    }

    private void transformToIndexed(int[] arr, int[] retArr) {
        int counter = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            temp = retArr[i];
            retArr[i] = counter;
            counter += temp;
        }
    }

    private void decideCounts(int[] retArr, int divide, int remain) {
        Arrays.fill(retArr, divide);
        for (int i = 0; i < remain; i++) {
            retArr[i]++;
        }
    }

    private void threadsMoreOrEqual(int[] arr, int min, int[] retArr) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            retArr[i] = counter;
            counter = counter + 1 % min;
        }
    }
}

