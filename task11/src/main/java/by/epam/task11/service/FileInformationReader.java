package by.epam.task11.service;

import by.epam.task11.dao.FileReaderDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;

public class FileInformationReader {

    private static final Logger log = LogManager.getLogger(FileInformationReader.class);


    private FileReaderDao fileReaderDao = new FileReaderDao();

    public String readFile(String fileName) throws IOException {
        log.info("readFile: fileName = {}", fileName);
        return fileReaderDao.readFile(fileName);
    }
}
