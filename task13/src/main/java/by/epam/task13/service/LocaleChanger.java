package by.epam.task13.service;

import java.util.Locale;

public class LocaleChanger {
    public Locale changeLocale(Locale current) {
        if (current.getCountry().equalsIgnoreCase("ru")) {
            return new Locale("en", "US");
        } else {
            return new Locale("RU", "ru");
        }
    }
}
