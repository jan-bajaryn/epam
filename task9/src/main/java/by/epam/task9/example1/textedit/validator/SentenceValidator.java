package by.epam.task9.example1.textedit.validator;

import by.epam.task9.example1.textedit.entity.*;

import java.util.List;
import java.util.Optional;

public class SentenceValidator {
    public boolean isValid(Sentence sentence) {
        if (sentence == null || sentence.getMembers() == null) {
            return false;
        }

        List<TextMember> members = sentence.getMembers();
        return !members.isEmpty()
                && checkFirstLast(members)
                && checkTwoSplitersOrEnd(members)
                && checkWords(members)
                && checkEndSymbolsMiddle(members);
    }

    private boolean checkWords(List<TextMember> members) {

        EndSentence[] endSentences = EndSentence.values();
        Spliter[] sptiters = Spliter.values();

        Optional<String> any = members.stream()
                .filter(m -> m instanceof Word)
                .map(m -> {
                    String s = m.textValue();
                    return s.substring(0, s.length() - 1);
                })
                .filter(s -> {
                    for (Spliter sptiter : sptiters) {
                        if (s.contains(sptiter.textValue())) {
                            return true;
                        }
                    }
                    for (EndSentence endSentence : endSentences) {
                        if (s.contains(endSentence.textValue())) {
                            return true;
                        }
                    }
                    return false;
                })
                .findAny();
        return any.isEmpty();

    }

    private boolean checkEndSymbolsMiddle(List<TextMember> members) {
        for (int i = 0; i < members.size() - 1; i++) {
            EndSentence[] values = EndSentence.values();
            for (EndSentence value : values) {
                if (members.get(i).textValue().contains(value.textValue())) {
                    return false;
                }
            }
        }
        return true;
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
