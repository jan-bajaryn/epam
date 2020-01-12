package by.epam.task10.example1.controller;

import by.epam.task10.example1.entity.Directory;
import by.epam.task10.example1.entity.File;
import by.epam.task10.example1.service.FileReader;
import by.epam.task10.example1.service.FileWriter;
import by.epam.task10.example1.service.InOutException;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        File file = new File("myName.txt", new Directory("D:\\where\\my\\enemy"));
        FileReader fileReader = new FileReader(file);
        FileWriter fileWriter = new FileWriter(file);
        Scanner scanner = new Scanner(System.in);
        try {
            fileWriter.create();
//            scanner.nextLine();
            fileWriter.rename("my new name to file.txt");
//            scanner.nextLine();
            fileWriter.append("my text very interesting");
            fileWriter.append("my text very interesting");
//            scanner.nextLine();
//            fileWriter.delete();
        } catch (InOutException e) {
            e.printStackTrace();
        }
    }
}
