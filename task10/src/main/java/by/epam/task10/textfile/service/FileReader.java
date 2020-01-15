package by.epam.task10.textfile.service;

import by.epam.task10.textfile.entity.FFile;

public class FileReader {
    private FFile fFile;

    public FileReader(FFile fFile) {
        this.fFile = fFile;
    }

    public String getTextData() throws InOutException {
        throw new InOutException("Illegal path to file.");
    }
}
