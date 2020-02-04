package by.epam.task09.example1.textedit.entity;

public class Word implements TextMember{
    private String text;

    public Word(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return text != null ? text.equals(word.text) : word.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public String textValue() {
        return text;
    }

    @Override
    public String toString() {
        return textValue();
    }
}
