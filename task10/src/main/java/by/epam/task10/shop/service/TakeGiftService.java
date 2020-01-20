package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.entity.Gift;
import by.epam.task10.shop.service.exception.EmptySweetsException;
import by.epam.task10.shop.service.exception.NullPackagingException;

public class TakeGiftService {
    private Purchases purchases = Purchases.getInstance();

    public void take() throws NullPackagingException, EmptySweetsException {
        Gift toAdd = purchases.getToAdd();
        if (toAdd.getPackaging() == null) {
            throw new NullPackagingException();
        }
        if (purchases.isEmptySweetsToAdd()) {
            throw new EmptySweetsException();
        }
        purchases.takeGift();
    }
}
