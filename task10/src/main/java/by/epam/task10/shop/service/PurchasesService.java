package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Purchases;

public class PurchasesService {
    private Purchases purchases = Purchases.getInstance();

    public boolean isEmptySweetsToAdd() {
        return purchases.isEmptySweetsToAdd();
    }

    public boolean isEmptyGiftList() {
        return purchases.isEmptyGiftList();
    }

    public void clear() {
        purchases.clear();
    }

    public boolean isNullPackaging() {
        return purchases.getToAdd().getPackaging() == null;
    }
}
