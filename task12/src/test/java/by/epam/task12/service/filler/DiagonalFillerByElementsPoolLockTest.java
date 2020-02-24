package by.epam.task12.service.filler;

import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.service.factory.MatrixElementsFactory;
import by.epam.task12.service.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.service.filler.DiagonalFillerByElementsPool;
import by.epam.task12.view.ShowMatrix;
import org.testng.annotations.Test;

public class DiagonalFillerByElementsPoolLockTest {

    private DiagonalFillerByElementsPool diagonalFillerByElementsPool = new DiagonalFillerByElementsPool();
    private MatrixElementsFactory matrixElementsFactory = new MatrixElementsFactory();
    private ShowMatrix showMatrix = new ShowMatrix();

    @Test
    public void testFill() throws IllegalArgsMatrixException {
        MatrixElements matrixElements = matrixElementsFactory.create(40, 40);
        diagonalFillerByElementsPool.fill(matrixElements, new int[]{1, 2, 3, 4});
        showMatrix.show(matrixElements);
    }
}