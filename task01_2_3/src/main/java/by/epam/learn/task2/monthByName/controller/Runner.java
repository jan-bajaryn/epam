package by.epam.learn.task2.monthByName.controller;

import by.epam.learn.task2.monthByName.domain.Season;
import by.epam.learn.task2.monthByName.exception.IllegalInputNumberMonthException;

public class Runner {

    public static void main(String[] args) {
        Runner runner = new Runner();
        try {
            System.out.println(runner.getSeasonByNum(3));
        } catch (IllegalInputNumberMonthException e) {
            System.out.println("There no so month.");
        }
    }
    String getSeasonByNum(int month) throws IllegalInputNumberMonthException {
        switch (month){
            case 1:
            case 2:
            case 12:
                return Season.WINTER.getSeasonText();
            case 3:
            case 4:
            case 5:
                return Season.SPRING.getSeasonText();
            case 6:
            case 7:
            case 8:
                return Season.SUMMER.getSeasonText();
            case 9:
            case 10:
            case 11:
                return Season.AUTUMN.getSeasonText();
            default:
                throw new IllegalInputNumberMonthException();
        }
    }
}