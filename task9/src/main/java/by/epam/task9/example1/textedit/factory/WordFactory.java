package by.epam.task9.example1.textedit.factory;

import by.epam.task9.example1.textedit.entity.Word;
import by.epam.task9.example1.textedit.validator.WordValidator;

public class WordFactory {
    private WordValidator wordValidator = new WordValidator();

    public Word create(String data) throws IllegalInputException {
        Word word = new Word(data);
        if (wordValidator.isValid(word)) {
            return word;
        } else {
            throw new IllegalInputException();
        }

    }
}
