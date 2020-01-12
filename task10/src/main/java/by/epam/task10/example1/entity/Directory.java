package by.epam.task10.example1.entity;


public class Directory {
    private String path;

    public Directory() {
        path = "";
    }

    public Directory(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public boolean isEmpty() {
        return "".equals(path);
    }

    public void setPath(String path) {
        this.path = path;
    }
}
