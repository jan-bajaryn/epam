package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.Gift;
import by.epam.task10.shop.entity.Packaging;

import java.util.Map;

public class ChangeOrGetPackaging {
    private Shop shop = Shop.getInstance();
    private Purchases purchases = Purchases.getInstance();

    public void changeOrGetPackagin(Integer index) throws InvalidIndexCountException, NoElementsToExchangeException {
        Map.Entry<Packaging, Integer> byIndex = shop.findPackEntrByIndex(index);
        if (byIndex == null) {
            throw new InvalidIndexCountException();
        }
        if (byIndex.getValue() < 1) {
            throw new NoElementsToExchangeException();
        }
        Gift gift = purchases.getToAdd();
        Packaging activePack = gift.getPackaging();

        Packaging shopPack = byIndex.getKey();
        if (activePack == null) {

            gift.setPackaging(shopPack);
            shop.reducePackaging(shopPack);
        } else {
            shop.putPackaging(activePack);
            gift.setPackaging(shopPack);
            shop.reducePackaging(shopPack);
        }

    }
}
