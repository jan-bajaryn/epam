package by.epam.demofunctions.entity;

public class Car {
    private String name;
    private boolean isFullDrive;
    private boolean isFullEngine;

    public Car(String name, boolean isFullDrive, boolean isFullEngine) {
        this.name = name;
        this.isFullDrive = isFullDrive;
        this.isFullEngine = isFullEngine;
    }

    public Car() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFullDrive() {
        return isFullDrive;
    }

    public void setFullDrive(boolean fullDrive) {
        isFullDrive = fullDrive;
    }

    public boolean isFullEngine() {
        return isFullEngine;
    }

    public void setFullEngine(boolean fullEngine) {
        isFullEngine = fullEngine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", isFullDrive=" + isFullDrive +
                ", isFullEngine=" + isFullEngine +
                '}';
    }
}
