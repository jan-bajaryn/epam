package by.epam.task10.shop.service;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.entity.Gift;
import by.epam.task10.shop.entity.Packaging;
import by.epam.task10.shop.entity.Sweet;
import by.epam.task10.shop.service.exception.ProblematicWriteFileException;
import by.epam.task10.shop.view.StringWriter;

import java.io.IOException;
import java.util.List;

public class PrintParagonService {
    private StringWriter stringWriter = new StringWriter();
    private Purchases purchases = Purchases.getInstance();

    public void print(String fileName) throws ProblematicWriteFileException {
        StringBuilder sb = new StringBuilder();

        List<Gift> all = purchases.findAll();

        for (int i = 0; i < all.size(); i++) {
            Gift gift = all.get(i);
            List<Sweet> sweets = gift.getSweets();
            Packaging packaging = gift.getPackaging();
            sb.append(i).append(". size: ").append(packaging.getSize().
                    getMaxSize()).append(", color")
                    .append(packaging.getColor()).append("\n");
            sb.append("\t").append("Sweets: \n");
            for (int j = 0; j < sweets.size(); j++) {
                Sweet sweet = sweets.get(j);
                sb.append("\t").append(j).append(". name: ")
                        .append(sweet.getName())
                        .append(", count-->").append(sweet.getCount());
            }
        }
        try {
            stringWriter.writeString(fileName, sb.toString());
        } catch (IOException e) {
            throw new ProblematicWriteFileException();
        }
    }
}
