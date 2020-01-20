package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.Gift;
import by.epam.task10.shop.entity.Packaging;
import by.epam.task10.shop.entity.Sweet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class StateFormatter {
    private Shop shop = Shop.getInstance();
    private Purchases purchases = Purchases.getInstance();

    public String format() {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Packaging, Integer>> entries = shop.findAllPackaging().entrySet();
        sb.append("Packages in the shop:\n");
        sb.append(packFormat(entries));

        List<Sweet> allSweets = shop.findAllSweets();
        sb.append("Sweets in the shop:\n");
        sb.append(sweetsFormat(allSweets));

        Gift toAdd = purchases.getToAdd();
        sb.append("Your not ready gift:\n");
        sb.append(oneGiftFormat(toAdd));

        sb.append("Ready Gifts:\n");
        List<Gift> all = purchases.findAll();
        sb.append(giftsFormat(all));

        return sb.toString();
    }

    private String giftsFormat(List<Gift> all) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < all.size(); i++) {
            Gift gift = all.get(i);
            sb.append("Number ").append(i).append(":\n");
            sb.append(oneGiftFormat(gift));
        }
        return sb.toString();
    }

    private String oneGiftFormat(Gift toAdd) {
        StringBuilder sb = new StringBuilder();
        Packaging curPack = toAdd.getPackaging();
        sb.append("Packaging: ");
        if (curPack != null) {
            sb.append("color: ").append(curPack.getColor()).
                    append(", size: ").append(curPack.getSize()).
                    append("\n");
        }
        sb.append("\nSweets: \n");
        List<Sweet> sweets = toAdd.getSweets();
        sb.append(sweetsFormat(sweets));
        return sb.toString();
    }

    private String sweetsFormat(List<Sweet> allSweets) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allSweets.size(); i++) {
            Sweet sweet = allSweets.get(i);
            sb.append(i).append(". name: ").
                    append(sweet.getName()).append(", count: ").
                    append(sweet.getCount()).append("\n");
        }
        return sb.toString();
    }

    private String packFormat(Set<Map.Entry<Packaging, Integer>> entries) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Map.Entry<Packaging, Integer> entry : entries) {
            sb.append(counter++).append(". color: ").
                    append(entry.getKey().getColor()).append(", size: ").
                    append(entry.getKey().getSize()).append(", count -->").
                    append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
