package by.epam.task10.calendar.dao;

import by.epam.task10.calendar.entity.FreeCelebrity;
import by.epam.task10.calendar.entity.IrregularDay;
import by.epam.task10.calendar.entity.RegularDay;
import by.epam.task10.calendar.entity.SpecDate;
import by.epam.task10.calendar.entity.dto.DayDTO;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Calendar {
    private FreeCelebrityManager manager;

    public Calendar(FreeCelebrityManager manager) {
        this.manager = manager;
    }

    public static class FreeCelebrityManager {
        private Set<RegularDay> regularDays;
        private Set<SpecDate> specificDays;
        private Set<IrregularDay> irregularDays;

        public FreeCelebrityManager(Set<RegularDay> regularDays, Set<SpecDate> specificDays, Set<IrregularDay> irregularDays) {
            this.regularDays = regularDays;
            this.specificDays = specificDays;
            this.irregularDays = irregularDays;
        }
    }

    public List<FreeCelebrity> calcFreeCelebritiesForDate(LocalDate date) {
        return Stream.concat(manager.regularDays.stream(), Stream.concat(manager.irregularDays.stream(), manager.specificDays.stream()))
                .filter(f -> f.isThatDay(date))
                .collect(Collectors.toList());
    }

    public Map<LocalDate, List<FreeCelebrity>> calcDays(final LocalDate minimum, final LocalDate maximum) {
        if (minimum.compareTo(maximum) > 0) {
            new ArrayList<>();
        }
        int diff = Period.between(minimum, maximum).getDays();
//        return IntStream.range(0, diff)
//                .mapToObj(minimum::plusDays)
//                .map(d -> new DayDTO(d, calcFreeCelebritiesForDate(d)))
//                .collect(Collectors.toList());
        return IntStream.range(0, diff)
                .mapToObj(minimum::plusDays)
                .collect(Collectors.toMap(d -> d, d -> calcFreeCelebritiesForDate(d)));
    }

}
