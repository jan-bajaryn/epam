package by.epam.task12.service.filler;

import by.epam.task12.entity.factory.MatrixAtomicImplFactory;
import by.epam.task12.entity.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.entity.impl.MatrixAtomicImpl;
import by.epam.task12.view.ShowMatrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class DiagonalFillerAtomicTest {
    private static final Logger log = LogManager.getLogger(DiagonalFillerAtomicTest.class);

    private DiagonalFillerAtomic diagonalFillerAtomic = new DiagonalFillerAtomic();
    private MatrixAtomicImplFactory matrixAtomicImplFactory = new MatrixAtomicImplFactory();
    private ShowMatrix showMatrix = new ShowMatrix();

    @Test
    public void testFill() throws IllegalArgsMatrixException {
        MatrixAtomicImpl matrixAtomic = matrixAtomicImplFactory.create(40, 40);
        diagonalFillerAtomic.fill(matrixAtomic, new int[]{1, 2, 3, 4});
        int min = Math.min(matrixAtomic.calcColumns(), matrixAtomic.calcRows());
        for (int i = 0; i < min; i++) {
            assertNotEquals(matrixAtomic.calcValue(i, i), 0);
        }
//        showMatrix.show(matrixAtomic);
    }
}