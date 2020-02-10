package by.epam.task11.service.impl;

import by.epam.task11.dao.FileReaderDao;

import java.io.IOException;

public class FileInformationReader {

    private FileReaderDao fileReaderDao = new FileReaderDao();

    public String readFile(String fileName) throws IOException {
        return fileReaderDao.readFile(fileName);
    }
}
