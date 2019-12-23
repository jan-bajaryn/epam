package by.epam.task8.controller;

import by.epam.task8.bin.domain.Test;
import by.epam.task8.service.TestService;
import by.epam.task8.view.View;

public class Runner {

    public static void main(String[] args) {
        TestService testService = new TestService();
        View view = new View();
        Test test = new Test(3,4);
        view.show(test.toString());
        view.show("Sum is"+testService.calcSum(test));

    }
}
