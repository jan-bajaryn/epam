package by.epam.task12.service.reader;

import by.epam.task12.dao.reader.LinesReader;
import by.epam.task12.entity.Matrix;
import by.epam.task12.entity.factory.MatrixFactory;
import by.epam.task12.entity.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.service.parser.ToIntArrayParser;
import by.epam.task12.service.reader.exception.IllegalFileNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MatrixFromFileMaker<T extends MatrixFactory<K>, K extends Matrix> {
    private static final Logger log = LogManager.getLogger(MatrixFromFileMaker.class);

    private LinesReader linesReader = new LinesReader();
    private ToIntArrayParser toIntArrayParser = new ToIntArrayParser();
    private T matrixFactory; /*= new MatrixElementsFactory();*/

    public MatrixFromFileMaker(T matrixFactory) {
        this.matrixFactory = matrixFactory;
    }

    public K make(String fileNameMatrix) throws IllegalFileNameException {
        try {
            String[] read = linesReader.read(fileNameMatrix);
            int[][] data = toIntArrayParser.parse(read);
            return matrixFactory.create(data);
        } catch (IOException | IllegalArgsMatrixException e) {
            log.info("Exception in make, message = {}", e.getMessage());
            throw new IllegalFileNameException(e);
        }
    }
}
