package by.epam.learn.task3.sumrecursion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SumRecursionFile {

    String fileName;

    public SumRecursionFile(String fileName) {
        this.fileName = fileName;
    }



    public int sumRecursion() throws IOException {
        Integer[] arr = getArrayFile();
        if (arr == null || arr.length == 0) return 0;
        return sumRecursionHelper(arr, 0);
    }

    private Integer[] getArrayFile() throws IOException {
        return Files.lines(Paths.get("numbers.txt"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }

    private int sumRecursionHelper(Integer[] arr, int i) {
        if (i >= arr.length) return 0;
        return arr[i] + sumRecursionHelper(arr, ++i);
    }
}
