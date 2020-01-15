package by.epam.task10.textfile.entity.factory;

import by.epam.task10.textfile.entity.Directory;
import by.epam.task10.textfile.entity.TextFFile;
import by.epam.task10.textfile.service.validator.FileValidator;

import java.util.regex.Pattern;

import static by.epam.task10.textfile.entity.TextFFile.EXTENSION;

public class TextFileFactory {

    private DirectoryFactory directoryFactory = new DirectoryFactory();
    private FileValidator fileValidator = new FileValidator();
    Pattern hasExt = Pattern.compile(".*\\." + EXTENSION, Pattern.CASE_INSENSITIVE);


    public TextFFile create(String fileName, String pathDirectory) throws IllegalPathException {
        try {
            if (pathDirectory != null && pathDirectory.isEmpty()) {
                pathDirectory = System.getProperty("user.dir");
            }
            if (fileName != null && hasTxtExtension(fileName)) {
                fileName = fileName.substring(fileName.length() - EXTENSION.length(), fileName.length());
            }

            Directory directory = directoryFactory.create(pathDirectory);
            TextFFile fFile = new TextFFile(fileName, directory);
            if (fileValidator.isValid(fFile)) {
                return fFile;
            }
        } catch (IllegalPathException e) {
            throw new IllegalPathException("Illegal path for directory in FFileFactory.", e);
        }
        throw new IllegalPathException("Illegal fileName in FFileFactory");
    }

    private boolean hasTxtExtension(String fileName) {
        return hasExt.matcher(fileName).matches();
    }
}
