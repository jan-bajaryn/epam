package by.epam.task9.example1.textedit.validator;

import by.epam.task9.example1.textedit.entity.EndSentence;
import by.epam.task9.example1.textedit.entity.Sentence;
import by.epam.task9.example1.textedit.entity.Spliter;
import by.epam.task9.example1.textedit.entity.TextMember;

import java.util.List;

public class SentenceValidator {
    public boolean isValid(Sentence sentence) {
        if (sentence == null || sentence.getMembers() == null) {
            return false;
        }

        List<TextMember> members = sentence.getMembers();
        return !members.isEmpty() && checkFirstLast(members) && checkTwoSplitersOrEnd(members);
    }

    private boolean checkTwoSplitersOrEnd(List<TextMember> members) {
        boolean isFirst = false;

        for (TextMember member : members) {
            if (isSpliterOrEnd(member)) {
                if (isFirst) {
                    return false;
                }
                isFirst = true;
            } else {
                isFirst = false;
            }
        }
        return true;
    }

    private boolean checkFirstLast(List<TextMember> members) {
        TextMember textMember = members.get(0);
        if (textMember.textValue().isEmpty() ||
                textMember.textValue().charAt(0) != textMember.textValue().toUpperCase().charAt(0) ||
                isSpliterOrEnd(textMember)) {
            return false;
        }
        textMember = members.get(members.size() - 1);
        return textMember instanceof EndSentence;
    }

    private boolean isSpliterOrEnd(TextMember textMember) {
        return (textMember instanceof EndSentence) || (textMember instanceof Spliter);
    }
}
