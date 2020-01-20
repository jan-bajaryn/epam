package by.epam.task10.shop.controller.command.dialog;

import by.epam.task10.shop.view.WrongInput;

public class Response {
    public static final String EXIT = "EXIT";

    private Request nextRequest;
    private String status;
    private WrongInput wrongInput;
    private String displayInformation;

    public Request getNextRequest() {
        return nextRequest;
    }

    public void setNextRequest(Request nextRequest) {
        this.nextRequest = nextRequest;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public WrongInput getWrongInput() {
        return wrongInput;
    }

    public void setWrongInput(WrongInput wrongInput) {
        this.wrongInput = wrongInput;
    }

    public String getDisplayInformation() {
        return displayInformation;
    }

    public void setDisplayInformation(String displayInformation) {
        this.displayInformation = displayInformation;
    }
}
