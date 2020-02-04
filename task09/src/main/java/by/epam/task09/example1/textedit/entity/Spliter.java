package by.epam.task09.example1.textedit.entity;

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
