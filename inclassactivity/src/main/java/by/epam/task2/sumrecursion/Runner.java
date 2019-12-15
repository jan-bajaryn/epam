package by.epam.task2.sumrecursion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// это 3-е задание
public class Runner {
    public static void main(String[] args) throws IOException {
//        Files.lines(Paths.get("numbers.txt")

        Integer[] arr = Files.lines(Paths.get("numbers.txt"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        Runner runner = new Runner();
        System.out.println(runner.sumRecursion(arr));
    }

    private int sumRecursion(Integer[] arr) {
        if (arr == null || arr.length == 0) return 0;
        return sumRecursionHelper(arr, 0);
    }

    private int sumRecursionHelper(Integer[] arr, int i) {
        if (i >= arr.length) return 0;
        return arr[i] + sumRecursionHelper(arr, ++i);
    }
}
