package by.epam.task12.service.parser;

import java.util.Arrays;

public class ToIntArrayParser {

    private static final String DILIMETER = "---";

    public int[][] parse(String[] read) {
        return Arrays.stream(read)
                .map(s -> s.split(DILIMETER))
                .map(this::toInts)
                .toArray(int[][]::new);
    }

    private int[] toInts(String[] s) {
        return Arrays.stream(s)
                .map(Integer::valueOf)
                .mapToInt(ss -> ss)
                .toArray();
    }
}
