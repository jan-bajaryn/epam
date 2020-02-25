package by.epam.task12.service.filler;

import by.epam.task12.entity.impl.MatrixAtomicImpl;
import by.epam.task12.service.CommonCounter;
import by.epam.task12.service.factory.CommonCounterFactory;
import by.epam.task12.service.filler.thread.AtomicFiller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class DiagonalFillerAtomic {

    private static final Logger log = LogManager.getLogger(DiagonalFillerAtomic.class);

    private CommonCounterFactory commonCounterFactory = new CommonCounterFactory();

    public void fill(MatrixAtomicImpl matrix, int[] arr) {

        int[] pos = decideBeginPositions(matrix, arr);
        log.info("pos = {}", pos);
        Thread[] threads = createThreads(arr, matrix, pos);
        startThreads(threads);
        joinThreads(threads);
    }

    private void joinThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.info("Integgupted joining thread = {}", thread.getName());
            }
        }
    }

    private void startThreads(Thread[] threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private Thread[] createThreads(int[] arr, MatrixAtomicImpl matrix, int[] pos) {
        CommonCounter commonCounter = commonCounterFactory.create(matrix);
        Thread[] threads = new Thread[arr.length];
        int position = 0;
        for (int i = 0; i < arr.length; i++) {
            threads[i] = new AtomicFiller(arr[i], position, matrix, commonCounter);
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
