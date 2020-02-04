package by.epam.learn.task2.monthByName.domain;

public enum Season {
    SPRING("Spring"),
    SUMMER("Summer"),
    AUTUMN("Autumn"),
    WINTER("Winter");

    private String seasonText;

    private Season(String seasonText) {
        this.seasonText = seasonText;
    }

    public String getSeasonText() {
        return seasonText;
    }
}
