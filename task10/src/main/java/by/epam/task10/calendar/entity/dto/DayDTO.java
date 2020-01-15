package by.epam.task10.calendar.entity.dto;

import by.epam.task10.calendar.entity.FreeCelebrity;

import java.time.LocalDate;
import java.util.List;

public class DayDTO {
    private LocalDate localDate;
    private List<FreeCelebrity> freeCelebrities;

    public DayDTO(LocalDate localDate, List<FreeCelebrity> freeCelebrities) {
        this.localDate = localDate;
        this.freeCelebrities = freeCelebrities;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public List<FreeCelebrity> getFreeCelebrities() {
        return freeCelebrities;
    }
}
