package by.epam.task10.shop.dao;

import by.epam.task10.shop.entity.Packaging;
import by.epam.task10.shop.entity.Sweet;

import java.util.*;

public class Shop {
    private List<Sweet> sweets = new ArrayList<>();
    private Map<Packaging, Integer> packages = new LinkedHashMap<>();


    private static Shop shop = new Shop();

    public static Shop getInstance() {
        return shop;
    }

    public void putSweet(Sweet s) {
        Sweet toAdd = sweets.stream()
                .filter(sweet -> s.getName().equals(sweet.getName()) && s.getSize() == sweet.getSize())
                .findAny().orElse(null);
        if (toAdd == null) {
            sweets.add(s);
        } else {
            toAdd.setCount(toAdd.getCount() + s.getCount());
        }
    }

    public void putPackaging(Packaging p) {
        if (packages.containsKey(p)) {
            packages.put(p, packages.get(p) + 1);
        }
        packages.put(p, 1);
    }


    //change to another collection
    public Packaging findByIndex(int index) {
        Set<Map.Entry<Packaging, Integer>> entries = packages.entrySet();
        int counter = 0;
        for (Map.Entry<Packaging, Integer> entry : entries) {
            if (counter == index) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Map<Packaging, Integer> findAllPackaging() {
        return packages;
    }

    public void reducePackaging(Packaging byIndex) {
        if (packages.containsKey(byIndex)) {
            packages.put(byIndex, packages.get(byIndex) - 1);
        }
    }
}
