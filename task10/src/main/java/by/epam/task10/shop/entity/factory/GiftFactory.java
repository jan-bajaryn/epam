package by.epam.task10.shop.entity.factory;

import by.epam.task10.shop.entity.Gift;

import java.util.ArrayList;

public class GiftFactory {
    public Gift create() {
        return new Gift(new ArrayList<>(), null);
    }
}
