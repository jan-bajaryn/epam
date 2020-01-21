package by.epam.task10.textfile.service;

import by.epam.task10.textfile.dao.FileWriter;
import by.epam.task10.textfile.dao.InOutException;
import by.epam.task10.textfile.entity.FFile;

public class FileWriterService {
    private FileWriter fileWriter = new FileWriter();

    public void append(String data, FFile fFile) throws InOutException {
        fileWriter.append(fFile, "\n" + data);
    }

    public void create(FFile fFile) throws InOutException {
        fileWriter.create(fFile);
    }

    public void delete(FFile fFile) throws InOutException {
        fileWriter.delete(fFile);
    }

    public void rename(FFile fFile, String fileName) throws InOutException {
        fileWriter.rename(fFile, fileName);
    }
}
