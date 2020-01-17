package by.epam.task10.calendar.service.formatter;

public class StringFormatter {
    public String formatNamDesc(String string) {
        return string.trim().replaceAll("\\s", " ");
    }
}
