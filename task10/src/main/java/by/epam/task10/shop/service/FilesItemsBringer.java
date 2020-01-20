package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.parser.exception.IllegalParamsPackingException;
import by.epam.task10.shop.parser.PackageParser;
import by.epam.task10.shop.parser.SweetParser;
import by.epam.task10.shop.parser.exception.WrongSweetParamsCountException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FilesItemsBringer {

    public static final String SPLITERATOR = "---";
    public static final int SWEET_PARAMS_COUNT = 3;
    public static final int PACK_PARAMS_COUNT = 2;

    private Shop shop = Shop.getInstance();

    private SweetParser sweetParser = new SweetParser();
    private PackageParser packageParser = new PackageParser();

    public void bring(String fileName) throws IOException {

        //TODO перенести чтение из файла в dao

        String[][] strings = Files.lines(Paths.get(fileName))
                .map(s -> s.split(SPLITERATOR))
                .toArray(String[][]::new);

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
        // TODO ДОБАВИТЬ В ФАКТОРКИ СОЗДАНИЕЯ С ПОМОЩЬЮ СТРОК
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
}
