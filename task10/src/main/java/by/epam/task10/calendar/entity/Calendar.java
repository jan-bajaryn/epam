package by.epam.task10.calendar.entity;

import by.epam.task10.calendar.entity.impl.RegularFreeCelebrity;
import by.epam.task10.calendar.entity.impl.SpecDate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Calendar implements Serializable {
    private String name;
    private FreeCelebrityManager manager = new FreeCelebrityManager();

    public Calendar(Set<RegularFreeCelebrity> freeCelebrities,
                    Set<SpecDate> specDates,
                    Set<IrregularFreeCelebrity> irregularFreeCelebrities) {
        manager = new FreeCelebrityManager();
        manager.setIrregularDays(irregularFreeCelebrities);
        manager.setRegularDays(freeCelebrities);
        manager.setSpecificDays(specDates);
    }

    public Calendar() {
    }

    public static class FreeCelebrityManager implements Serializable {
        private Set<RegularFreeCelebrity> regularDays = new HashSet<>();
        private Set<SpecDate> specificDays = new HashSet<>();
        private Set<IrregularFreeCelebrity> irregularDays = new HashSet<>();

        public FreeCelebrityManager() {
            //empty for allowing xml serialization
        }

        public Set<RegularFreeCelebrity> getRegularDays() {
            return regularDays;
        }

        public void setRegularDays(Set<RegularFreeCelebrity> regularDays) {
            this.regularDays = regularDays;
        }

        public Set<SpecDate> getSpecificDays() {
            return specificDays;
        }

        public void setSpecificDays(Set<SpecDate> specificDays) {
            this.specificDays = specificDays;
        }

        public Set<IrregularFreeCelebrity> getIrregularDays() {
            return irregularDays;
        }

        public void setIrregularDays(Set<IrregularFreeCelebrity> irregularDays) {
            this.irregularDays = irregularDays;
        }
    }


    public boolean addRegularDay(RegularFreeCelebrity regularDay) {
        return manager.regularDays.add(regularDay);
    }

    public boolean addSpecDay(SpecDate specDate) {
        return manager.specificDays.add(specDate);
    }

    public boolean addIrregularDay(IrregularFreeCelebrity irregularDay) {
        return manager.irregularDays.add(irregularDay);
    }

    public void clear() {
        manager.irregularDays.clear();
        manager.specificDays.clear();
        manager.regularDays.clear();
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
