package by.epam.task9.example1.textedit.factory;

import by.epam.task9.example1.textedit.entity.EndSentence;
import by.epam.task9.example1.textedit.entity.Sentence;
import by.epam.task9.example1.textedit.entity.Spliter;
import by.epam.task9.example1.textedit.entity.TextMember;
import by.epam.task9.example1.textedit.validator.SentenceValidator;

import java.util.ArrayList;
import java.util.List;

public class SentenceFactory {

    private WordFactory wordFactory = new WordFactory();
    private SentenceValidator sentenceValidator = new SentenceValidator();

    public Sentence create(String data) throws IllegalInputException {
        if (data == null) {
            throw new IllegalInputException();
        }

        data = data.trim();

        List<TextMember> list = new ArrayList<>();

        String[] splitSpaces = data.split(" +");

        Spliter[] spliters = Spliter.values();
        EndSentence[] endSentences = EndSentence.values();

        boolean flag = true;
        for (String splitSpace : splitSpaces) {

            for (EndSentence endSentence : endSentences) {
                if (splitSpace.endsWith(endSentence.textValue())) {
                    list.add(wordFactory.create(splitSpace.substring(0, splitSpace.length() - 1)));
                    list.add(endSentence);
                    flag = false;
                    break;
                }
            }

            if (flag) {
                for (Spliter spliter : spliters) {
                    if (splitSpace.endsWith(spliter.textValue())) {
                        list.add(wordFactory.create(splitSpace.substring(0, splitSpace.length() - 1)));
                        list.add(spliter);
                        flag = false;
                        break;
                    }

                }
            }
            if (flag){
                list.add(wordFactory.create(splitSpace));
            }


            flag = true;

        }
        Sentence sentence = new Sentence(list);

        if (sentenceValidator.isValid(sentence)) {
            return sentence;
        } else {
            throw new IllegalInputException();
        }
    }
}
