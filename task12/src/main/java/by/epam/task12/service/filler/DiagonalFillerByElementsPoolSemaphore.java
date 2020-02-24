package by.epam.task12.service.filler;

import by.epam.task12.dao.ElementsPoolSemaphore;
import by.epam.task12.entity.impl.Element;
import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.service.filler.thread.SingleFillerSemaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DiagonalFillerByElementsPoolSemaphore {
    private ElementsPoolSemaphore elementsPoolLock = ElementsPoolSemaphore.getInstance();
    private static final Logger log = LogManager.getLogger(DiagonalFillerByElementsPool.class);

    public void fill(MatrixElements matrix, int[] arr) {
        if (matrix == null) {
            return;
        }

        List<Element> elements = new ArrayList<>();
        int rows = matrix.calcRows();
        int columns = matrix.calcColumns();

        collectElements(matrix, elements, rows, columns);
        putElements(elements);
        List<Thread> threads = execFill(arr);
        joinAll(threads);
    }

    private void joinAll(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.info("Interrupted in joinAll.");
            }
        }
    }

    private List<Thread> execFill(int[] arr) {
        List<Thread> threads = new ArrayList<>();
        for (int i : arr) {
            SingleFillerSemaphore fillerThread = new SingleFillerSemaphore(i);
            threads.add(fillerThread);
            fillerThread.start();
        }
        return threads;
    }

    private void putElements(List<Element> elements) {
        for (Element element : elements) {
            elementsPoolLock.putElement(element);
        }
    }

    private void collectElements(MatrixElements matrix, List<Element> elements, int rows, int columns) {
        int min = Integer.min(rows, columns);
        for (int i = 0; i < min; i++) {
            Element element = matrix.getElement(i, i);
            elements.add(element);
        }
    }
}
