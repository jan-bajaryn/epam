package by.epam.task10.textfile.controller.command.dialog;

import by.epam.task10.textfile.entity.FFile;

import java.util.List;

public class Request {
    private FFile fFile;
    private List<Object> data;
    private String stringData;

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

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    public String getStringData() {
        return stringData;
    }
}
