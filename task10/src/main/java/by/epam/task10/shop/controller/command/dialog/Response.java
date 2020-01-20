package by.epam.task10.shop.controller.command.dialog;

public class Response {
    public static final String EXIT = "EXIT";

    private Request nextRequest;
    private String status;
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


    public String getDisplayInformation() {
        return displayInformation;
    }

    public void setDisplayInformation(String displayInformation) {
        this.displayInformation = displayInformation;
    }
}
