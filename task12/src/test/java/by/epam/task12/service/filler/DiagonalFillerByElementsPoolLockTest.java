package by.epam.task12.service.filler;

import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.entity.factory.MatrixElementsFactory;
import by.epam.task12.entity.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.view.ShowMatrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class DiagonalFillerByElementsPoolLockTest {

    private static final Logger log = LogManager.getLogger(DiagonalFillerByElementsPoolLockTest.class);

    private DiagonalFillerByElementsPool diagonalFillerByElementsPool = new DiagonalFillerByElementsPool();
    private MatrixElementsFactory matrixElementsFactory = new MatrixElementsFactory();

    @Test
    public void testFill() throws IllegalArgsMatrixException {
        MatrixElements matrixElements = matrixElementsFactory.create(40, 40);
        diagonalFillerByElementsPool.fill(matrixElements, new int[]{1, 2, 3, 4});
        log.info("matrix = {}", matrixElements);
    }
}