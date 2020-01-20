package by.epam.task10.shop.dao;

import by.epam.task10.shop.entity.Gift;
import by.epam.task10.shop.entity.Sweet;
import by.epam.task10.shop.entity.factory.GiftFactory;

import java.util.ArrayList;
import java.util.List;

public class Purchases {
    private GiftFactory giftFactory = new GiftFactory();

    private List<Gift> gifts = new ArrayList<>();
    private Gift toAdd = giftFactory.create();

    private Purchases() {}

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

    public Gift getToAdd() {
        return toAdd;
    }

    public void setToAdd(Gift toAdd) {
        this.toAdd = toAdd;
    }
    public boolean isEmptySweetsToAdd(){
        List<Sweet> sweets = toAdd.getSweets();
        if (sweets.isEmpty()) {
            return true;
        }
        for (Sweet sweet : sweets) {
            if (sweet.getCount()!=0){
                return false;
            }
        }
        return true;
    }
}
