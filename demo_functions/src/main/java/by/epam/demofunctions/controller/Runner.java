package by.epam.demofunctions.controller;

import by.epam.demofunctions.entity.Car;

import java.util.function.BiPredicate;

public class Runner {
    public static void main(String[] args) {
//        BiPredicate<String ,String > pred =
        Car audi1 = new Car("audi1", true, true);
        Car audi2 = new Car("audi2", false, false);

        CheckCar checkCar = new CheckCar() {
            @Override
            public boolean test(Car car) {
                return car.isFullDrive();
            }
        };
        printTest(audi1, new CheckCar() {
            @Override
            public boolean test(Car car) {
                return car.isFullDrive();
            }
        });
        printTest(audi1, car -> car.isFullDrive());
        printTest(audi1, Car::isFullDrive);
        printTest(audi2, checkCar);
    }

    private static void printTest(Car car, CheckCar checker) {
        if (checker.test(car)) {
            System.out.println(car);
        }
    }
}
