package by.epam.task9.example1.textedit.factory;

import by.epam.task9.example1.textedit.entity.EndSentence;
import by.epam.task9.example1.textedit.entity.Sentence;
import by.epam.task9.example1.textedit.entity.Text;

import java.util.ArrayList;
import java.util.List;

public class TextFactory {
    private SentenceFactory sentenceFactory = new SentenceFactory();

    public Text create(String header, String data) throws IllegalInputException {
        if (data == null) {
            throw new IllegalInputException();
        }

        List<Sentence> list = new ArrayList<>();
        data = data.trim();
        int fstIndex = 0;
        int secIndex = 0;
        char[] chars = data.toCharArray();
        EndSentence[] values = EndSentence.values();
        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {

            for (EndSentence value : values) {
                if (chars[i] == value.textValue().charAt(0)) {
                    secIndex = i;
                    flag = true;
                    break;
                }
            }

            if (flag) {
                list.add(sentenceFactory.create(data.substring(fstIndex, secIndex + 1)));
                fstIndex = secIndex + 1;
                secIndex = secIndex + 1;
                flag = false;
            }
        }

        return new Text(header, list);
    }
}