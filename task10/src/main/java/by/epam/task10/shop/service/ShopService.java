package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.Packaging;
import by.epam.task10.shop.entity.Sweet;

import java.util.List;
import java.util.Map;

public class ShopService {
    private Shop shop = Shop.getInstance();

    public boolean isEmptyPackages() {
        return shop.findAllPackaging().isEmpty();
    }

    public Map<Packaging, Integer> findAllPackaging() {
        return shop.findAllPackaging();
    }

    public List<Sweet> findAllSweets() {
        return shop.findAllSweets();
    }
}
