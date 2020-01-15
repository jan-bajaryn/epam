package by.epam.task10.textfile.entity.factory;

import by.epam.task10.textfile.entity.Directory;
import by.epam.task10.textfile.service.validator.DirectoryValidator;

public class DirectoryFactory {

    private DirectoryValidator directoryValidator = new DirectoryValidator();

    public Directory create(String data) throws IllegalPathException {
        Directory directory = new Directory(data);
        if (directoryValidator.isValid(directory)) {
            return directory;
        }
        throw new IllegalPathException("Path is incorrect.");

    }
}
