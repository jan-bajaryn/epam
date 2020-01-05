package by.epam.learn.task3.sumtotext.runner;

import by.epam.learn.task3.sumtotext.parser.exception.IllegalInputSumTextException;
import by.epam.learn.task3.sumtotext.parser.SumToTextParser;

public class Runner {
    public static void main(String[] args) {
        SumToTextParser sumToTextParser = new SumToTextParser();
        try {
            System.out.println(sumToTextParser.parseText(3_345));
        } catch (IllegalInputSumTextException e) {
            System.out.println("Illegal input. Use integer more than 0 and less than 99_999");
        }
    }
}
