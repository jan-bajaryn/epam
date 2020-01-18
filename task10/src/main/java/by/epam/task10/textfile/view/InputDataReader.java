package by.epam.task10.textfile.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputDataReader {
    private Scanner sc = new Scanner(System.in);

    public List<Object> readCreationData() {

        List<Object> list = new ArrayList<>();
        System.out.println("Please enter 'true' if you want to create TextFile, or 'false' if you want to create Ffile.");
        Boolean isTextFile;
        try {
            isTextFile = sc.nextBoolean();
        } finally {
            sc.nextLine();
        }
        System.out.println("Please enter path to file. Or leave epmty if you want to create file in project directory.");
        String path = sc.nextLine();
        System.out.println("Please enter name of the file. Use letters, spaces or numbers.");
        String fileName = sc.nextLine();
        String extension = null;
        if (!isTextFile) {
            System.out.println("Enter extension of your file if you don't want to change it after. Leave empty if you want change.");
            extension = sc.nextLine();
        }
        list.add(isTextFile);
        list.add(path);
        list.add(fileName);
        list.add(extension);
        return list;
    }

    public String readAppendData() {
        System.out.println("Please enter a line to append.");
        return sc.nextLine();
    }

    public String readFileName() {
        System.out.println("Please enter a new fileName for this file.");
        return sc.nextLine();
    }
}
