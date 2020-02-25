package by.epam.task12.service.filler;

import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.entity.factory.MatrixElementsFactory;
import by.epam.task12.entity.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.view.ShowMatrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class DiagonalFillerByElementsPoolSemaphoreTest {
    private static final Logger log = LogManager.getLogger(DiagonalFillerByElementsPoolSemaphoreTest.class);


    private DiagonalFillerByElementsPoolSemaphore diagonalFillerByElementsPool =
            new DiagonalFillerByElementsPoolSemaphore();
    private MatrixElementsFactory matrixElementsFactory = new MatrixElementsFactory();
    private ShowMatrix showMatrix = new ShowMatrix();


    @Test
    public void testFill() throws IllegalArgsMatrixException {
        MatrixElements matrixElements = matrixElementsFactory.create(40, 40);
        diagonalFillerByElementsPool.fill(matrixElements, new int[]{1, 2, 3, 4});
        int min = Math.min(matrixElements.calcColumns(), matrixElements.calcRows());
        for (int i = 0; i < min; i++) {
            assertNotEquals(matrixElements.calcValue(i, i), 0);
        }
//        showMatrix.show(matrixElements);
    }
}