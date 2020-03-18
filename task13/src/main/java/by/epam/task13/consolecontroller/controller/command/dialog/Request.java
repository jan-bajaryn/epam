package by.epam.task13.consolecontroller.controller.command.dialog;


import java.util.Locale;

public class Request {

    private String fileNameMatrix;
    private String method;

    public String getFileNameMatrix() {
        return fileNameMatrix;
    }

    public void setFileNameMatrix(String fileNameMatrix) {
        this.fileNameMatrix = fileNameMatrix;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
