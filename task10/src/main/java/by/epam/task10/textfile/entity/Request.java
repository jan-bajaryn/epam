package by.epam.task10.textfile.entity;

import java.util.List;

public class Request {
    private FFile fFile;
    private List<Object> data;

    public FFile getfFile() {
        return fFile;
    }

    public void setfFile(FFile fFile) {
        this.fFile = fFile;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public List<Object> getData() {
        return data;
    }
}
