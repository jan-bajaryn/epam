package by.epam.task10.textfile.controller;

import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.entity.factory.IllegalPathException;
import by.epam.task10.textfile.entity.factory.TextFileFactory;
import by.epam.task10.textfile.service.FileReader;
import by.epam.task10.textfile.service.FileWriter;
import by.epam.task10.textfile.service.InOutException;

import java.io.IOException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws IOException {

//        try {
//            Scanner scanner = new Scanner(System.in);
//            TextFileFactory fFileFactory = new TextFileFactory();
//            System.out.println("enter file name:");
//            System.out.println("Enter path to file:");
//            FFile file = fFileFactory.create(scanner.nextLine(), scanner.nextLine());
//            FileReader fileReader = new FileReader(file);
//            FileWriter fileWriter = new FileWriter(file);
//
//            fileWriter.create();
//            System.out.println("rename to:");
//            fileWriter.rename(scanner.nextLine());
//            System.out.println("append text:");
//            fileWriter.append(scanner.nextLine());
//            System.out.println("append another text");
//            fileWriter.append(scanner.nextLine());
//            System.out.println("do you want to delete this file?");
//            boolean isDelete = scanner.nextBoolean();
//            if (isDelete) {
//                fileWriter.delete();
//            }
//        } catch (InOutException e) {
//            e.printStackTrace();
//        } catch (IllegalPathException e) {
//            e.printStackTrace();
//        }

        Controller controller = new Controller();
        controller.run();


    }
}
