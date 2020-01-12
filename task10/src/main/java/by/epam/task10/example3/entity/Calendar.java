package by.epam.task10.example3.entity;

import java.util.List;

public class Calendar {

    private List<SpecDate> regularDays;
    private List<SpecDate> specificDays;
    private List<IrregularDay> irregularDays;

    public Calendar(List<SpecDate> regularDays, List<SpecDate> specificDays, List<IrregularDay> irregularDays) {
        this.regularDays = regularDays;
        this.specificDays = specificDays;
        this.irregularDays = irregularDays;
    }

    public class SpecDate extends FreeCelebrity {
        private Date date;
        public SpecDate(boolean selebrity, boolean free, Date date) {
            super(selebrity, free);
            this.date = date;
        }

    }

    public List<SpecDate> getRegularDays() {
        return regularDays;
    }

    public List<SpecDate> getSpecificDays() {
        return specificDays;
    }

    public List<IrregularDay> getIrregularDays() {
        return irregularDays;
    }
}
