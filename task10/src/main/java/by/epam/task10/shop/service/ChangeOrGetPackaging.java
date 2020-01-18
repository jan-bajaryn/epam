package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.Gift;
import by.epam.task10.shop.entity.Packaging;
import by.epam.task10.shop.entity.factory.PackagingFactory;

public class ChangeOrGetPackaging {
    private Shop shop = Shop.getInstance();
    public void changeOrGetPackagin(Gift gift, Integer index) {
        Packaging byIndex = shop.findByIndex(index);
        if (byIndex !=null){
            gift.setPackaging(byIndex);
            shop.reducePackaging(byIndex);
        }
    }
}
