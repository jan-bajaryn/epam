package by.epam.task10.calendar.entity.impl;

import by.epam.task10.calendar.entity.IrregularFreeCelebrity;

import java.time.LocalDate;

public final class CatholicEaster extends IrregularFreeCelebrity {
    public CatholicEaster() {
        super(true, true, "Catholic easter every year.", "Catholic easter");
    }


    @Override
    public boolean isThatDay(LocalDate localDate) {
        int year = localDate.getYear();
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int g = (8 * b + 13) / 25;
        int h = (19 * a + b - d - g + 15) % 30;
        int j = c / 4;
        int k = c % 4;
        int m = (a + 11 * h) / 319;
        int r = (2 * e + 2 * j - k - h + m + 32) % 7;
        int n = (h - m + r + 90) / 25;
        int p = (h - m + r + n + 19) % 32;
        return localDate.equals(LocalDate.of(year, n, p));
    }
}
