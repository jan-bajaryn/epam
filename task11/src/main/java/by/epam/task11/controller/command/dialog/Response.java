package by.epam.task11.controller.command.dialog;

public class Response {
    public static final String EXIT = "EXIT";
    private String status;
    private Request nextRequest;
    private String displayInformation;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Request getNextRequest() {
        return nextRequest;
    }

    public void setNextRequest(Request nextRequest) {
        this.nextRequest = nextRequest;
    }

    public String getDisplayInformation() {
        return displayInformation;
    }

    public void setDisplayInformation(String displayInformation) {
        this.displayInformation = displayInformation;
    }
}
