package by.epam.task12.service.reader;

import by.epam.task12.dao.reader.LinesReader;
import by.epam.task12.entity.factory.impl.MatrixElementsFactory;
import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.service.parser.ToIntArrayParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MarixFromFileMaker {
    private static final Logger log = LogManager.getLogger(MarixFromFileMaker.class);

    private LinesReader linesReader = new LinesReader();
    private ToIntArrayParser toIntArrayParser = new ToIntArrayParser();
    private MatrixElementsFactory matrixElementsFactory = new MatrixElementsFactory();

    public MatrixElements make(String fileNameMatrix) {
        try {
            String[] read = linesReader.read(fileNameMatrix);
            int[][] data = toIntArrayParser.parse(read);
            matrixElementsFactory.create(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }
}
