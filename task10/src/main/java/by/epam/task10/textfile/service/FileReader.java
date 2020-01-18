package by.epam.task10.textfile.service;

import by.epam.task10.textfile.entity.FFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public String getTextData(FFile fFile) throws InOutException {
        try {
            return new String(Files.readAllBytes(Paths.get(fFile.calcFullPath())));
        } catch (IOException e) {
            throw new InOutException("Illegal path to file.");
        }
    }
}
