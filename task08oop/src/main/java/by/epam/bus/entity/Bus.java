package by.epam.bus.entity;

public class Bus {
    private Person driver;
    private int busNumber;
    private int trackNumber;
    private String stamp;
    private int beginYear;
    private int millage;

    public Bus(Person driver, int busNumber, int trackNumber, String stamp, int beginYear, int millage) {
        this.driver = driver;
        this.busNumber = busNumber;
        this.trackNumber = trackNumber;
        this.stamp = stamp;
        this.beginYear = beginYear;
        this.millage = millage;
    }

    public Person getDriver() {
        return driver;
    }

    public void setDriver(Person driver) {
        this.driver = driver;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public int getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(int beginYear) {
        this.beginYear = beginYear;
    }

    public int getMillage() {
        return millage;
    }

    public void setMillage(int millage) {
        this.millage = millage;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "driver=" + driver +
                ", busNumber=" + busNumber +
                ", trackNumber=" + trackNumber +
                ", stamp='" + stamp + '\'' +
                ", beginYear=" + beginYear +
                ", millage=" + millage +
                '}';
    }
}
