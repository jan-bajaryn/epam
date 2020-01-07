package by.epam.task9.example1.textedit.entity;

public enum Spliter implements TextMember {
    COMMA(","),
    SEMICOLON(";"),
//    SPACE(" "),
    COLON(":");

    private String text;

    Spliter(String text) {
        this.text = text;
    }

    @Override
    public String textValue() {
        return text;
    }
}
