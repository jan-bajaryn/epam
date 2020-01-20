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
                .filter(sweet -> s.getName().equals(sweet.getName()))
                .findAny().orElse(null);
        if (toAdd == null) {
            sweets.add(s);
        } else {
            int index = sweets.indexOf(toAdd);
            sweets.set(index, toAdd.setCount(toAdd.getCount() + s.getCount()));
        }
    }

    public void putPackaging(Packaging p) {
        if (packages.containsKey(p)) {
            packages.put(p, packages.get(p) + 1);
        } else {
            packages.put(p, 1);
        }
    }


    //change to another collection
    public Map.Entry<Packaging, Integer> findPackEntrByIndex(int index) {
        Set<Map.Entry<Packaging, Integer>> entries = packages.entrySet();
        int counter = 0;
        for (Map.Entry<Packaging, Integer> entry : entries) {
            if (counter++ == index) {
                return entry;
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

    public List<Sweet> findAllSweets() {
        return sweets;
    }

    public Sweet findSweetByIndex(int index) {
        if (index < 0 || index > sweets.size()) {
            return null;
        }
        Sweet byIndex = sweets.get(index);
        if (byIndex.getCount() < 1) {
            return null;
        }
        return byIndex;
    }
}
