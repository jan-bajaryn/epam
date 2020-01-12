package by.epam.task10.example1.service;

import by.epam.task10.example1.entity.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {
    public static final String ILLEGAL_PATH_TO_FILE = "Illegal path to file.";
    private File file;

    public FileWriter(File file) {
        this.file = file;
    }

    public void create() throws InOutException {
        try {
            java.io.File newFile = new java.io.File(this.file.calcFullPath());

            java.io.File parentFile = newFile.getAbsoluteFile().getParentFile();
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

    public void rename(String newName) throws InOutException {
        java.io.File oldFile = new java.io.File(this.file.calcFullPath());
        java.io.File newFile = new java.io.File(this.file.getDirectory().getPath() + java.io.File.separator + newName);
        if (!oldFile.renameTo(newFile)) {
            throw new InOutException("File with so name is already exists.");
        }
//        System.out.println("newFile.getName() = " + newFile.getName());
//        System.out.println("newFile.getParent() = " + newFile.getAbsoluteFile().getParent());
//        return new File(newFile.getName(), new Directory(newFile.getAbsoluteFile().getParent()));
        file.setName(newFile.getName());
        file.getDirectory().setPath(newFile.getAbsoluteFile().getParent());
    }


    public void append(String data) throws InOutException {
        try {
//            System.out.println("this.file.getDirectory().getPath() = " + this.file.getDirectory().getPath());
//            System.out.println("this.file.calcFullPath() = " + this.file.calcFullPath());
            Files.write(Paths.get(this.file.calcFullPath()), data.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new InOutException(ILLEGAL_PATH_TO_FILE, e);
        }
    }

    public void delete() throws InOutException {
        java.io.File file = new java.io.File(this.file.calcFullPath());
        boolean isDelete = file.delete();
        if (!isDelete) {
            throw new InOutException(ILLEGAL_PATH_TO_FILE);
        }
    }
}
