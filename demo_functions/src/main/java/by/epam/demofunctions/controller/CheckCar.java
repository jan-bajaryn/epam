package by.epam.demofunctions.controller;

import by.epam.demofunctions.entity.Car;

public interface CheckCar {
    boolean test(Car car);

    default boolean test1() {
        return true;
    }

}
