package by.epam.task10.textfile.service;

import by.epam.task10.textfile.dao.FileReader;
import by.epam.task10.textfile.dao.InOutException;
import by.epam.task10.textfile.entity.FFile;

public class FileReaderService {
    private FileReader fileReader = new FileReader();

    public String getTextData(FFile fFile) throws InOutException {
        return fileReader.getTextData(fFile);
    }
}
