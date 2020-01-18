package by.epam.task10.textfile.service;

import by.epam.task10.textfile.entity.FFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {
    public static final String ILLEGAL_PATH_TO_FILE = "Illegal path to file.";

    public void create(FFile fFile) throws InOutException {
        try {
            File newFile = new File(fFile.calcFullPath());

            File parentFile = newFile.getAbsoluteFile().getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            boolean isCreated = newFile.createNewFile();
            if (!isCreated) {
                throw new InOutException("File with so name already exists");
            }
        } catch (IOException e) {
            throw new InOutException(ILLEGAL_PATH_TO_FILE, e);
        }

    }

    public void rename(FFile fFile, String newName) throws InOutException {
        File oldFile = new File(fFile.calcFullPath());
        File newFile = new File(fFile.getDirectory().getPath() + File.separator + newName + fFile.getExtension());
        if (!oldFile.renameTo(newFile)) {
            throw new InOutException("File with so name is already exists.");
        }
//        System.out.println("newFile.getName() = " + newFile.getName());
//        System.out.println("newFile.getParent() = " + newFile.getAbsoluteFile().getParent());
//        return new File(newFile.getName(), new Directory(newFile.getAbsoluteFile().getParent()));
        fFile.setName(newName);
        fFile.getDirectory().setPath(newFile.getAbsoluteFile().getParent());
    }


    public void append(FFile fFile, String data) throws InOutException {
        try {
//            System.out.println("this.file.getDirectory().getPath() = " + this.file.getDirectory().getPath());
//            System.out.println("this.file.calcFullPath() = " + this.file.calcFullPath());
            Files.write(Paths.get(fFile.calcFullPath()), data.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new InOutException(ILLEGAL_PATH_TO_FILE, e);
        }
    }

    public void delete(FFile fFile) throws InOutException {
        File file = new File(fFile.calcFullPath());
//        boolean isDelete = file.delete();
        try {
            Files.delete(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            throw new InOutException(ILLEGAL_PATH_TO_FILE);
        }
    }
}
