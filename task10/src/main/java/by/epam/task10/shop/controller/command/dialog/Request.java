package by.epam.task10.shop.controller.command.dialog;

public class Request {
    private Integer index;
    private String stringData;

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
