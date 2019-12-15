package by.epam.task2.monthByName.controller;

public enum Season {
    SPRING("Spring"),
    SUMMER("Summer"),
    AUTUMN("Autumn"),
    WINTER("Winter"),
    FAIL("Fail");

    private String seasonText;

    private Season(String seasonText) {
        this.seasonText = seasonText;
    }

    public String getSeasonText() {
        return seasonText;
    }
}
