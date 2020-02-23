package by.epam.task12.service;

import by.epam.task12.dao.ElementsPool;
import by.epam.task12.entity.impl.MatrixImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiagonalFillerByElementsPool {
    ElementsPool elementsPool = ElementsPool.getInstance();
    private static final Logger log = LogManager.getLogger(DiagonalFillerByElementsPool.class);
    public void fill(MatrixImpl matrixImpl, int[] arr) {
        if (matrixImpl == null) {
            return;
        }



    }

}
