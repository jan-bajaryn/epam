package by.epam.learn.task2.monthByName.controller;

public class Runner {

    public static void main(String[] args) throws IllegalInputNumberMonthException {
        Runner runner = new Runner();
        System.out.println(runner.getSeasonByNum(3));
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