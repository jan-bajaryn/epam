package by.epam.task09.example1.textedit.entity;

import java.util.List;

public class Sentence {
    private List<TextMember> members;


    public Sentence(List<TextMember> members) {
        this.members = members;
    }

    public List<TextMember> getMembers() {
        return members;
    }

    public void setMembers(List<TextMember> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sentence sentence = (Sentence) o;

        return members != null ? members.equals(sentence.members) : sentence.members == null;
    }

    @Override
    public int hashCode() {
        return members != null ? members.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "members=" + members +
                '}';
    }
}
