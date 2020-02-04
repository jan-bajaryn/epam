package by.epam.learn.task3.sumrecursion;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) {

        SumRecursionFile sumRecursionFile = new SumRecursionFile("numbers.txt");
        try {
            
            System.out.println(sumRecursionFile.sumRecursion());

        } catch (IOException e) {
            System.out.println("No such file found");
        } catch (NumberFormatException e) {
            System.out.println("Format of numbers in file is wrong");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

}
