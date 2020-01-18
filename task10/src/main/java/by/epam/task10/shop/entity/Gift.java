package by.epam.task10.shop.entity;

import java.util.List;

public class Gift {
    private List<Sweet> sweets;
    private Packaging packaging;

    public Gift(List<Sweet> sweets, Packaging packaging) {
        this.sweets = sweets;
        this.packaging = packaging;
    }

    public Gift() {
    }

    public List<Sweet> getSweets() {
        return sweets;
    }

    public void setSweets(List<Sweet> sweets) {
        this.sweets = sweets;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }
}
