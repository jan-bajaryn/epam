package by.epam.task09.example1.textedit.entity;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private String head;
    private List<Sentence> sentences;

    public Text(String head, List<Sentence> sentences) {
        this.head = head;
        this.sentences = sentences;
    }

    public Text(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public Text() {
        sentences = new ArrayList<>();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }
}
