package by.epam.task10.calendar.service;

import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.entity.FreeCelebrity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;

public class CalendarService {
    public void appendCalendar(Calendar calendar, Calendar calToAppend) {
        Calendar.FreeCelebrityManager manager = calendar.getManager();
        Calendar.FreeCelebrityManager managerAppend = calToAppend.getManager();
        manager.getRegularDays().addAll(managerAppend.getRegularDays());
        manager.getIrregularDays().addAll(managerAppend.getIrregularDays());
        manager.getSpecificDays().addAll(managerAppend.getSpecificDays());
    }

    public Map<LocalDate, List<FreeCelebrity>> calcDays(Calendar calendar, final LocalDate minimum, final LocalDate maximum) {
        if (minimum.compareTo(maximum) > 0) {
            new TreeMap<>();
        }

        int diff = (int) DAYS.between(minimum, maximum);
        return new TreeMap<>(IntStream.range(0, diff)
                .mapToObj(minimum::plusDays)
                .collect(Collectors.toMap(d -> d, d -> calcFreeCelebritiesForDate(calendar, d))));
    }

    public List<FreeCelebrity> calcFreeCelebritiesForDate(Calendar calendar, LocalDate date) {
        return Stream.concat(calendar.getManager().getRegularDays().stream(),
                Stream.concat(calendar.getManager().getIrregularDays().stream(),
                        calendar.getManager().getSpecificDays().stream()))
                .filter(f -> f.isThatDay(date))
                .collect(Collectors.toList());
    }


}
