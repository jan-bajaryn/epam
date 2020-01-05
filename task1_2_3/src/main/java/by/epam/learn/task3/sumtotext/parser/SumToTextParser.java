package by.epam.learn.task3.sumtotext.parser;

import by.epam.learn.task3.sumtotext.parser.exception.IllegalInputSumTextException;

import java.util.Map;
import java.util.TreeMap;

public class SumToTextParser {

    public static Map<Integer, String> G_VOC = new TreeMap<>();

    public static Map<Integer, String> VOC = new TreeMap<>();

    static {
        VOC.put(0, "");
        VOC.put(1, "Один");
        VOC.put(2, "Два");
        VOC.put(3, "Три");
        VOC.put(4, "Четыре");
        VOC.put(5, "Пять");
        VOC.put(6, "Шесть");
        VOC.put(7, "Семь");
        VOC.put(8, "Восемь");
        VOC.put(9, "Девять");
        VOC.put(10, "Десять");
        VOC.put(11, "Одиннадцать");
        VOC.put(12, "Двенадцать");
        VOC.put(13, "Тринадцать");
        VOC.put(14, "Четырнадцать");
        VOC.put(15, "Пятнадцать");
        VOC.put(16, "Шестнадцать");
        VOC.put(17, "Семнадцать");
        VOC.put(18, "Восемнадцать");
        VOC.put(19, "Девятнадцать");
        VOC.put(20, "Двадцать");
        VOC.put(30, "Тридцать");
        VOC.put(40, "Сорок");
        VOC.put(50, "Пятьдесят");
        VOC.put(60, "Шестьдесят");
        VOC.put(70, "Семдесят");
        VOC.put(80, "Восемдесят");
        VOC.put(90, "Девяносто");
        VOC.put(100, "Сто");
        VOC.put(200, "Двести");
        VOC.put(300, "Триста");
        VOC.put(400, "Четыреста");
        VOC.put(500, "Пятьсот");
        VOC.put(600, "Шестьсот");
        VOC.put(700, "Семсот");
        VOC.put(800, "Восемсот");
        VOC.put(900, "Девятьсот");
        VOC.put(1_000, "Тысяча");

        G_VOC.put(0, "");
        G_VOC.put(1, "Одна");
        G_VOC.put(2, "Две");
        G_VOC.put(3, "Три");
        G_VOC.put(4, "Четыре");
        G_VOC.put(5, "Пять");
        G_VOC.put(6, "Шесть");
        G_VOC.put(7, "Семь");
        G_VOC.put(8, "Восемь");
        G_VOC.put(9, "Девять");
    }


    public String parseText(int n) throws IllegalInputSumTextException {

        if (n > 99_999 || n < 0) {
            throw new IllegalInputSumTextException();
        }


        char[] arr = Integer.toString(n).toCharArray();
        StringBuilder sb = new StringBuilder();
        if (arr.length == 5) {
            int buf = Character.getNumericValue(arr[0]) * 10;
            sb.append(VOC.get(buf));
            sb.append(" ");
        }
        if (arr.length >= 4) {
            int buf = Character.getNumericValue(arr[arr.length - 4]);
            sb.append(G_VOC.get(buf));
            if (buf != 0) {
                sb.append(" ");
            }
            sb.append(beginParse(buf));
            sb.append(" ");
        }
        if (arr.length >= 3) {
            int buf = Character.getNumericValue(arr[arr.length - 3]) * 100;
            sb.append(VOC.get(buf));
            if (buf != 0) {
                sb.append(" ");
            }
        }
        if (arr.length >= 2) {
            int buf = Character.getNumericValue(arr[arr.length - 2]) * 10;
            sb.append(VOC.get(buf));
            if (buf != 0) {
                sb.append(" ");
            }
        }
        if (arr.length == 1) {

            int buf = Character.getNumericValue(arr[0]);
            if (buf != 0) {
                sb.append(VOC.get(buf));
            } else {
                sb.append("Ноль");
            }
            sb.append(" ");
            sb.append(endParse(buf));

        } else if (arr.length >= 1) {
            int buf = Character.getNumericValue(arr[arr.length - 1]);
            sb.append(VOC.get(buf));
            if (buf != 0) {
                sb.append(" ");
            }
            sb.append(endParse(buf));
        }
        return sb.toString();
    }


    private String beginParse(int n) {
        switch (n) {
            case 1:
                return "Тысяча";
            case 2:
            case 3:
            case 4:
                return "Тысячи";
            case 0:
            case 5:
            case 6:
            case 7:
            case 9:
                return "Тысяч";
            default:
                throw new IllegalArgumentException("Enter integer from 1 to 9");
        }
    }


    private String endParse(int n) {
        switch (n) {
            case 1:
                return "Рубль";
            case 2:
            case 3:
            case 4:
                return "Рубля";
            case 0:
            case 5:
            case 6:
            case 7:
            case 9:
                return "Рублей";
            default:
                throw new IllegalArgumentException("Enter integer from 1 to 9");
        }
    }


}
