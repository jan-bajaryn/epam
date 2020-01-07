package by.epam.task9.example1.textedit.entity;

public enum EndSentence implements TextMember {
    DOT("."),
    QUESTION("?"),
    EXCLAMATION("!");


    private String text;

    EndSentence(String text) {
        this.text = text;
    }

    @Override
    public String textValue() {
        return text;
    }
}
