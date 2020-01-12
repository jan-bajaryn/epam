package by.epam.task10.example1.service;

import by.epam.task10.example1.entity.File;

public class FileReader {
    private File file;

    public FileReader(File file) {
        this.file = file;
    }

    public String getTextData() throws InOutException {
        throw new InOutException("Illegal path to file.");
    }
}
