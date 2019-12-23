package by.epam.bus.dao;

public class Bus {
    private String surname;
    private int busNumber;
    private int trackNumber;
    private String stamp;
    private int beginYear;
    private int millage;

    public Bus(String surname, int busNumber, int trackNumber, String stamp, int beginYear, int millage) {
        this.surname = surname;
        this.busNumber = busNumber;
        this.trackNumber = trackNumber;
        this.stamp = stamp;
        this.beginYear = beginYear;
        this.millage = millage;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
                "surname='" + surname + '\'' +
                ", busNumber=" + busNumber +
                ", trackNumber=" + trackNumber +
                ", stamp='" + stamp + '\'' +
                ", beginYear=" + beginYear +
                ", millage=" + millage +
                '}';
    }
}
