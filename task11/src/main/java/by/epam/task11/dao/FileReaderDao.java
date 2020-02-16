package by.epam.task11.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderDao {

    private static final Logger log = LogManager.getLogger(FileReaderDao.class);


    public String readFile(String fileName) throws IOException {
        log.info("readFile: fileName= {}", fileName);
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
