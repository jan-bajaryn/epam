package by.epam.task2.monthByName.controller;

public class Runner {
//
//    public static final String WINTER = "Winter";
//    public static final String SPRING = "Spring";
//    public static final String SUMMER = "Summer";
//    public static final String AUTUMN = "Autumn";
//
//    public static void main(String[] args) throws IllegalInputNumberMonthException {
//        Runner runner = new Runner();
//        System.out.println(runner.getMonthName(6));
//    }
//
//    private String getMonthName(int number) throws IllegalInputNumberMonthException {
//        switch (number) {
//            case 1:
//            case 2:
//            case 12:
//                return WINTER;
//            case 3:
//            case 4:
//            case 5:
//                return SPRING;
//            case 6:
//            case 7:
//            case 8:
//                return SUMMER;
//            case 9:
//            case 10:
//            case 11:
//                return AUTUMN;
//            default:
//                throw new IllegalInputNumberMonthException();
//        }
//    }

    public static void main(String[] args) throws IllegalInputNumberMonthException {
        Runner runner = new Runner();
        runner.getSeasonByNum(3);
    }
    String getSeasonByNum(int month) throws IllegalInputNumberMonthException {
//        Season[] values = Season.values();
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