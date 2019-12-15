package by.epam.learn.task3.sumrecursion;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {

        SumRecursionFile sumRecursionFile = new SumRecursionFile("numbers.txt");
        System.out.println(sumRecursionFile.sumRecursion());
    }

}
