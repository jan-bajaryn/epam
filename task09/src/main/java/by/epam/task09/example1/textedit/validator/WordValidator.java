package by.epam.task09.example1.textedit.validator;

import by.epam.task09.example1.textedit.entity.EndSentence;
import by.epam.task09.example1.textedit.entity.Spliter;
import by.epam.task09.example1.textedit.entity.Word;

public class WordValidator {
    public boolean isValid(Word word) {
        if (word != null && word.getText() != null && !word.getText().isEmpty()) {
            Spliter[] values = Spliter.values();
            EndSentence[] endSentences = EndSentence.values();
            for (EndSentence endSentence : endSentences) {
                if (word.getText().contains(endSentence.textValue())) {
                    return false;
                }
            }
            for (Spliter s : values) {
                if (word.getText().contains(s.textValue())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
