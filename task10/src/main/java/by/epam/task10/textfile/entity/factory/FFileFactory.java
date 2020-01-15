package by.epam.task10.textfile.entity.factory;

import by.epam.task10.textfile.entity.Directory;
import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.service.validator.FileValidator;

public class FFileFactory {
    public static final String EMPTY = "";
    private DirectoryFactory directoryFactory = new DirectoryFactory();
    private FileValidator fileValidator = new FileValidator();

    public FFile create(String fileName, String pathDirectory, String extension) throws IllegalPathException {
        try {
            if (pathDirectory != null && pathDirectory.isEmpty()) {
                pathDirectory = System.getProperty("user.dir");
            }

            Directory directory = directoryFactory.create(pathDirectory);
            FFile fFile = new FFile(fileName, directory, extension);
            if (fileValidator.isValid(fFile)) {
                return fFile;
            }
        } catch (IllegalPathException e) {
            throw new IllegalPathException("Illegal path for directory in FFileFactory.", e);
        }
        throw new IllegalPathException("Illegal fileName in FFileFactory");
    }
}
