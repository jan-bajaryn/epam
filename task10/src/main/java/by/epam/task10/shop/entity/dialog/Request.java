package by.epam.task10.shop.entity.dialog;

import by.epam.task10.shop.entity.Gift;

public class Request {
    private Integer index;
    private Gift gift;

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
