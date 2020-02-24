package by.epam.task12.service.filler;

import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.service.factory.MatrixElementsFactory;
import by.epam.task12.service.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.view.ShowMatrix;
import org.testng.annotations.Test;

public class DiagonalFillerByElementsPoolSemaphoreTest {

    private DiagonalFillerByElementsPoolSemaphore diagonalFillerByElementsPool =
            new DiagonalFillerByElementsPoolSemaphore();
    private ShowMatrix showMatrix = new ShowMatrix();
    private MatrixElementsFactory matrixElementsFactory = new MatrixElementsFactory();


    @Test
    public void testFill() throws IllegalArgsMatrixException {
        MatrixElements matrixElements = matrixElementsFactory.create(40, 40);
        diagonalFillerByElementsPool.fill(matrixElements, new int[]{1, 2, 3, 4});
        showMatrix.show(matrixElements);
    }
}