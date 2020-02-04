package by.epam.task08.service;

import by.epam.task08.bin.domain.Test;

public class TestService {
    public void update(Test test, Integer a, Integer b) {
        test.setA(a);
        test.setB(b);
    }

    public int calcSum(Test test) {
        return test.getA() + test.getB();
    }

    public int findMax(Test test) {
        int a = test.getA();
        int b = test.getB();
        return Math.max(a, b);
    }
}
