package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.Gift;
import by.epam.task10.shop.entity.Sweet;
import by.epam.task10.shop.entity.factory.exception.IllegalFactParamSweetException;
import by.epam.task10.shop.entity.factory.SweetFactory;
import by.epam.task10.shop.service.exception.IllegalIndexSweetException;
import by.epam.task10.shop.service.exception.NoSweetFoundException;
import by.epam.task10.shop.service.exception.NotContainsPackagingException;
import by.epam.task10.shop.service.exception.NotEnoughSizeException;

import java.util.List;

public class TakeSweetService {
    private Shop shop = Shop.getInstance();
    private Purchases purchases = Purchases.getInstance();
    private SweetFactory sweetFactory = new SweetFactory();

    public void takeSweet(int index) throws IllegalIndexSweetException, NoSweetFoundException, IllegalFactParamSweetException, NotContainsPackagingException, NotEnoughSizeException {
        //TODO make realization

        Gift toAdd = purchases.getToAdd();
        List<Sweet> allSweets = shop.findAllSweets();
        checkParams(index, toAdd, allSweets);
        Sweet sweetByIndex = shop.findSweetByIndex(index);
        checkNull(sweetByIndex);
        List<Sweet> destinationSweets = toAdd.getSweets();
        int indexDest = destinationSweets.indexOf(sweetByIndex);
        if (indexDest != -1) {
            Sweet sweet = destinationSweets.get(indexDest);
            int newCount = sweet.getCount() + 1;
            if (newCount <= toAdd.getPackaging().getSize().getMaxSize()) {
                destinationSweets.set(indexDest, sweet.setCount(newCount));
                allSweets.set(index, sweetByIndex.setCount(sweetByIndex.getCount() - 1));
            } else {
                throw new NotEnoughSizeException();
            }
        } else {
            if (1 <= toAdd.getPackaging().getSize().getMaxSize()) {
                destinationSweets.add(sweetFactory.create(sweetByIndex.getName(), 1));
                allSweets.set(index, sweetByIndex.setCount(sweetByIndex.getCount() - 1));
            } else {
                throw new NotEnoughSizeException();
            }
        }
    }

    private void checkNull(Sweet sweetByIndex) throws NoSweetFoundException {
        if (sweetByIndex == null) {
            throw new NoSweetFoundException();
        }
    }

    private void checkParams(int index, Gift toAdd, List<Sweet> allSweets) throws NotContainsPackagingException, IllegalIndexSweetException {
        if (toAdd.getPackaging() == null) {
            throw new NotContainsPackagingException();
        }
        if (index < 0 || index > allSweets.size()) {
            throw new IllegalIndexSweetException();
        }
    }
}
