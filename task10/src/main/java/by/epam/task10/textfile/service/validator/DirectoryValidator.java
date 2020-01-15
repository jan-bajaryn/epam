package by.epam.task10.textfile.service.validator;

import by.epam.task10.textfile.entity.Directory;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class DirectoryValidator {

    public static final Pattern directoryPattern = Pattern.compile("\\.?[^.]+");

    //method isDirectory contains isInvalid check
    public boolean isValid(Directory directory) {
        if (directory == null || directory.getPath() == null) {
            return false;
        }

        File file = new File(directory.getPath());
        if (file.exists()) {
            return file.isDirectory();
        }

        try {
            Paths.get(directory.getPath());
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }

        return isOneParentExists(file) && directoryPattern.matcher(file.getName()).matches();
    }

    private boolean isOneParentExists(File file) {
        File parentFile = file;
        while (parentFile != null) {
            if (parentFile.exists()) {
                return true;
            }
            parentFile = parentFile.getParentFile();
        }
        return false;

    }
}
