package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.Sweet;
import by.epam.task10.shop.service.exception.IllegalSizeException;
import by.epam.task10.shop.service.exception.NoElementsToExchangeException;

import java.util.List;

public class RemoveSweetService {
    //TODO realize this method
    private Shop shop = Shop.getInstance();
    private Purchases purchases = Purchases.getInstance();

    public void remove(int index) throws IllegalSizeException, NoElementsToExchangeException {
        List<Sweet> sweets = purchases.getToAdd().getSweets();
        if (index < 0 || index > sweets.size()) {
            throw new IllegalSizeException();
        }
        Sweet current = sweets.get(index);
        if (current.getCount() < 1) {
            throw new NoElementsToExchangeException();
        }
        sweets.set(index, current.setCount(current.getCount() - 1));
        shop.putSweet(current);
    }
}
