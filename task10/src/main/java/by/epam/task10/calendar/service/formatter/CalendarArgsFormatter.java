package by.epam.task10.calendar.service.formatter;

import by.epam.task10.calendar.entity.impl.RegularFreeCelebrity;
import by.epam.task10.calendar.entity.impl.SpecDate;

import java.util.Set;

public class CalendarArgsFormatter {
    private StringFormatter stringFormatter = new StringFormatter();

    public void format(Set<RegularFreeCelebrity> regularDays, Set<SpecDate> specificDays) {
        if (regularDays != null) {
            for (RegularFreeCelebrity regularDay : regularDays) {
                if (regularDay.getName() != null) {
                    regularDay.setName(stringFormatter.formatNamDesc(regularDay.getName()));
                }
                if (regularDay.getDescription() != null) {
                    regularDay.setDescription(stringFormatter.formatNamDesc(regularDay.getDescription()));
                }
            }
        }
        if (specificDays != null) {
            for (SpecDate specificDay : specificDays) {
                if (specificDay.getName() != null) {
                    specificDay.setName(stringFormatter.formatNamDesc(specificDay.getName()));
                }
                if (specificDay.getDescription() != null) {
                    specificDay.setDescription(stringFormatter.formatNamDesc(specificDay.getDescription()));
                }
            }
        }
    }
}
