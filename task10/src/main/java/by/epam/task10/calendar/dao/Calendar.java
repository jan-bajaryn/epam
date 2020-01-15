package by.epam.task10.calendar.dao;

import by.epam.task10.calendar.entity.FreeCelebrity;
import by.epam.task10.calendar.entity.IrregularDay;
import by.epam.task10.calendar.entity.RegularDay;
import by.epam.task10.calendar.entity.SpecDate;
import by.epam.task10.calendar.entity.dto.RegularDayDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Calendar implements Serializable {
    private String name;
    private FreeCelebrityManager manager = new FreeCelebrityManager();

    public static class FreeCelebrityManager implements Serializable {
        private Set<RegularDay> regularDays = new HashSet<>();
        private Set<SpecDate> specificDays = new HashSet<>();
        private Set<IrregularDay> irregularDays = new HashSet<>();

        public FreeCelebrityManager() {
        }

        public Set<RegularDay> getRegularDays() {
            return regularDays;
        }

        public void setRegularDays(Set<RegularDay> regularDays) {
            this.regularDays = regularDays;
        }

        public Set<SpecDate> getSpecificDays() {
            return specificDays;
        }

        public void setSpecificDays(Set<SpecDate> specificDays) {
            this.specificDays = specificDays;
        }

        public Set<IrregularDay> getIrregularDays() {
            return irregularDays;
        }

        public void setIrregularDays(Set<IrregularDay> irregularDays) {
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
            new TreeMap<>();
        }
        int diff = Period.between(minimum, maximum).getDays();
        return new TreeMap<>(IntStream.range(0, diff)
                .mapToObj(minimum::plusDays)
                .collect(Collectors.toMap(d -> d, this::calcFreeCelebritiesForDate)));
    }

    public boolean addRegularDay(RegularDay regularDay) {
        return manager.regularDays.add(regularDay);
    }

    public boolean addSpecDay(SpecDate specDate) {
        return manager.specificDays.add(specDate);
    }

    public boolean addIrregularDay(IrregularDay irregularDay) {
        return manager.irregularDays.add(irregularDay);
    }

    public void clear() {
        manager.irregularDays.clear();
        manager.specificDays.clear();
        manager.regularDays.clear();
    }

    public Calendar() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FreeCelebrityManager getManager() {
        return manager;
    }

    public void setManager(FreeCelebrityManager manager) {
        this.manager = manager;
    }
}
