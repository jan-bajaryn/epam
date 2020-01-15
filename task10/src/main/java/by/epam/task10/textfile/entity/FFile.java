package by.epam.task10.textfile.entity;

public class FFile {
    private String name;
    private Directory directory;
    private final String extension;

    public FFile(String name, Directory directory, String extension) {
        this.name = name;
        this.directory = directory;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public Directory getDirectory() {
        return directory;
    }

    public String calcFullPath() {
        return directory.isEmpty() ? getName() : directory.getPath() + java.io.File.separator + getName() + getExtension();
    }

    public String getExtension() {
        return extension;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }
}
