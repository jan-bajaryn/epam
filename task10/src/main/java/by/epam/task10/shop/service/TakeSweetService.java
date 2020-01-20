package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.Gift;
import by.epam.task10.shop.entity.Sweet;
import by.epam.task10.shop.entity.factory.IllegalFactParamSweetException;
import by.epam.task10.shop.entity.factory.SweetFactory;

import java.util.List;

public class TakeSweetService {
    private Shop shop = Shop.getInstance();
    private Purchases purchases = Purchases.getInstance();
    private SweetFactory sweetFactory = new SweetFactory();

    public void takeSweet(int index) throws IllegalIndexSweetException, NoSweetFoundException, IllegalFactParamSweetException, NotContainsPackagingException, NotEnoughSizeException {
        //TODO make realization

        Gift toAdd = purchases.getToAdd();
        if (toAdd.getPackaging() == null) {
            throw new NotContainsPackagingException();
        }
        List<Sweet> allSweets = shop.findAllSweets();
        if (index < 0 || index > allSweets.size()) {
            throw new IllegalIndexSweetException();
        }
        Sweet sweetByIndex = shop.findSweetByIndex(index);
        if (sweetByIndex == null) {
            throw new NoSweetFoundException();
        }
        List<Sweet> destinationSweets = toAdd.getSweets();
        int indexDest = destinationSweets.indexOf(sweetByIndex);
        if (indexDest != -1) {
            Sweet sweet = destinationSweets.get(indexDest);
            int newSize = sweet.getCount() + 1;
            if (sweet.getSize() * newSize <= toAdd.getPackaging().getSize().getMaxSize()) {
                sweet.setCount(newSize);
                sweetByIndex.setCount(sweetByIndex.getCount() - 1);
            } else {
                throw new NotEnoughSizeException();
            }
        } else {

            if (sweetByIndex.getSize() <= toAdd.getPackaging().getSize().getMaxSize()) {
                destinationSweets.add(sweetFactory.create(sweetByIndex.getName(), sweetByIndex.getSize(), 1));
                sweetByIndex.setCount(sweetByIndex.getCount() - 1);
            }else {
                throw new NotEnoughSizeException();
            }
        }
    }
}
