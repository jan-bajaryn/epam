package by.epam.task12.service.reader;

import by.epam.task12.dao.reader.LinesReader;
import by.epam.task12.service.reader.exception.IllegalFileNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

public class ArrayIntMaker {
    private static final Logger log = LogManager.getLogger(ArrayIntMaker.class);


    private LinesReader linesReader = new LinesReader();

    public int[] make(String fileNameArr) throws IllegalFileNameException {

        try {
            String[] data = linesReader.read(fileNameArr);
            return Arrays.stream(data)
                    .map(Integer::valueOf)
                    .mapToInt(x -> x)
                    .toArray();
        } catch (NumberFormatException | IOException e) {
            log.info("Exception in make method = {}", e.getMessage());
            throw new IllegalFileNameException(e);
        }

    }
}
