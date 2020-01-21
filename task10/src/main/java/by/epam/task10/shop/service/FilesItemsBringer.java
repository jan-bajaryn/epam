package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.dao.reader.LinesReader;
import by.epam.task10.shop.parser.PackageParser;
import by.epam.task10.shop.parser.SweetParser;
import by.epam.task10.shop.parser.exception.IllegalParamsPackingException;
import by.epam.task10.shop.parser.exception.WrongSweetParamsCountException;

import java.io.IOException;
import java.util.Arrays;

public class FilesItemsBringer {

    public static final String DELIMITER = "---";
    public static final int SWEET_PARAMS_COUNT = 3;
    public static final int PACK_PARAMS_COUNT = 2;

    private Shop shop = Shop.getInstance();

    private SweetParser sweetParser = new SweetParser();
    private PackageParser packageParser = new PackageParser();

    private LinesReader linesReader = new LinesReader();

    public void bring(String fileName) throws IOException {

        String[] fromFile = linesReader.readFile(fileName);

        String[][] strings = Arrays.stream(fromFile)
                .map(s -> s.split(DELIMITER))
                .toArray(String[][]::new);

        sweetBring(strings);
        packBring(strings);
    }

    private void packBring(String[][] strings) {
        Arrays.stream(strings)
                .filter(s -> s.length == PACK_PARAMS_COUNT)
                .map(s -> {
                    try {
                        return packageParser.parseStringToPackaging(s);
                    } catch (IllegalParamsPackingException e) {
                        return null;
                    }
                })
                .forEach(p -> shop.putPackaging(p));
    }

    private void sweetBring(String[][] strings) {
        Arrays.stream(strings)
                .filter(s -> s.length == SWEET_PARAMS_COUNT)
                .map(s -> {
                    try {
                        return sweetParser.parseStringToSweet(s);
                    } catch (WrongSweetParamsCountException e) {
                        return null;
                    }
                })
                .forEach(s -> {
                    if (s != null) {
                        shop.putSweet(s);
                    }
                });
    }
}
