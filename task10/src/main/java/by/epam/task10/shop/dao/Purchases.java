package by.epam.task10.shop.dao;

import by.epam.task10.shop.entity.Gift;

import java.util.ArrayList;
import java.util.List;

public class Purchases {
    private List<Gift> gifts = new ArrayList<>();

    private static Purchases instance = new Purchases();

    public static Purchases getInstance() {
        return instance;
    }

    public void put(Gift gift) {
        gifts.add(gift);
    }

    public List<Gift> findAll() {
        return gifts;
    }

    public void deleteByNumber(int index) {
        gifts.remove(index);
    }

}
