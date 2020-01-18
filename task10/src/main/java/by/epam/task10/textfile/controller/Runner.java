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

        Controller controller = new Controller();
        controller.run();


    }
}
