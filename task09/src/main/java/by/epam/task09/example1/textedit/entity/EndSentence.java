package by.epam.task09.example1.textedit.entity;

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
