package by.epam.task10.calendar.parser;

import by.epam.task10.calendar.entity.FreeCelebrity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FormatRespList {
    public String parse(Map<LocalDate, List<FreeCelebrity>> map) {
        return map.entrySet().stream()
                .map(e -> {
                    StringBuilder sb = new StringBuilder(e.getKey().toString() + "->\t");
                    for (FreeCelebrity freeCelebrity : e.getValue()) {
                        sb.append("\n\t\t");
                        enterName(sb, freeCelebrity);
                        enterIfCondition(sb, freeCelebrity.isCelebrity(), " Celebration;");
                        enterIfCondition(sb, freeCelebrity.isFree(), " Free;");
                        enterIfCondition(sb.append("\t").append("Description: ").append(freeCelebrity.getDescription()),
                                !freeCelebrity.getDescription().isEmpty(),
                                ";");
                    }
                    return sb.append("\n");
                })
                .reduce(StringBuilder::append)
                .orElse(new StringBuilder()).toString();
    }

    public void enterIfCondition(StringBuilder sb, boolean condition, String s) {
        if (condition) {
            sb.append(s);
        }
    }

    public void enterName(StringBuilder sb, FreeCelebrity freeCelebrity) {
        if (!freeCelebrity.getName().isEmpty()) {
            sb.append(freeCelebrity.getName()).append(": ");
        } else {
            sb.append("No name: ");
        }
    }
}
