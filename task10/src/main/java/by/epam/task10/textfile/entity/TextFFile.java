package by.epam.task10.textfile.entity;

public class TextFFile extends FFile {
    public static final String EXTENSION = ".txt";

    public TextFFile(String name, Directory directory) {
        super(name, directory, EXTENSION);
    }
}
