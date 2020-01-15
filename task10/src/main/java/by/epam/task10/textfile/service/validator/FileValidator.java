package by.epam.task10.textfile.service.validator;

import by.epam.task10.textfile.entity.FFile;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class FileValidator {
    public static final Pattern fileNamePattern = Pattern.compile("[^\\\\\\\\/]+");
    public static final Pattern hasExtension = Pattern.compile(".*\\.[a-zA-Z]+");
    private DirectoryValidator directoryValidator = new DirectoryValidator();

    public boolean isValid(FFile fFile) {
        if (fFile == null ||
                !directoryValidator.isValid(fFile.getDirectory()) ||
                fFile.getName() == null ||
                fFile.getExtension() == null
        ) {
            return false;
        }

        //      boolean isValidExtension = hasExtension.matcher(fFile.getExtension()).matches() || fFile.getExtension().isEmpty();
//
//        if (!isValidExtension){
//            return false
//        }
        if (fFile.getName().isEmpty() && fFile.getExtension().isEmpty()) {
            return false;
        }
        if (!(hasExtension.matcher(fFile.getExtension()).matches() || fFile.getExtension().isEmpty())) {
            return false;
        }

        if (!fileNamePattern.matcher(fFile.getName()).matches()) {
            return false;
        }


        File file = new File(fFile.calcFullPath());

        if (file.exists()) {
            return file.isFile();
        }
        try {
            Paths.get(file.getAbsolutePath());
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }


}
