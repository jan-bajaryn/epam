package by.epam.task10.calendar.service;

import by.epam.task10.calendar.entity.FreeCelebrity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FormatRespList {
    public String format(Map<LocalDate, List<FreeCelebrity>> map) {
        return map.entrySet().stream()
                .map(e -> {
                    StringBuilder sb = new StringBuilder(e.getKey().toString() + "->\t");
                    for (FreeCelebrity freeCelebrity : e.getValue()) {
                        sb.append("\n\t\t");
                        if (!freeCelebrity.getName().isEmpty()) {
                            sb.append(freeCelebrity.getName()).append(": ");
                        } else {
                            sb.append("No name: ");
                        }
                        if (freeCelebrity.isCelebrity()) {
                            sb.append(" Celebration;");
                        }
                        if (freeCelebrity.isFree()) {
                            sb.append(" Free;");
                        }
                        if (!freeCelebrity.getDescription().isEmpty()) {
                            sb.append("\t").append("Description: ").append(freeCelebrity.getDescription()).append(";");
                        }
                    }
                    return sb.append("\n");
                })
                .reduce(StringBuilder::append)
                .orElse(new StringBuilder()).toString();
    }
}
