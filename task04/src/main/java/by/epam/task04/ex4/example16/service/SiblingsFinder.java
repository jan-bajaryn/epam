package by.epam.task04.ex4.example16.service;

import by.epam.task04.ex4.example16.entity.Pair;
import by.epam.task04.ex4.example16.service.exceptiion.IllegalInputMinMaxException;

import java.util.ArrayList;
import java.util.List;

public class SiblingsFinder {


    public List<Pair> findSiblings(int min, int max) throws IllegalInputMinMaxException {

        if (max<min){
            throw new IllegalInputMinMaxException();
        }

        List<Integer> simpleNum = findSimpleNum(min, max);
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < simpleNum.size() - 1; i++) {
            if (simpleNum.get(i) + 2 == simpleNum.get(i + 1)) {
                pairs.add(new Pair(simpleNum.get(i), simpleNum.get(i+1)));
            }
        }
        return pairs;
    }


    private List<Integer> findSimpleNum(int min, int max) {
        List<Integer> simpleNum = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (isSimple(i)) {
                simpleNum.add(i);
            }
        }
        return simpleNum;
    }

    private boolean isSimple(int num) {

        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i < num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
