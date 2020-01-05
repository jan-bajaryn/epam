package by.epam.learn.task2.date.creator;


import by.epam.learn.task2.date.entity.Date;
import by.epam.learn.task2.date.creator.exception.IncorrectParametersException;
import by.epam.learn.task2.date.parser.Parser;
import by.epam.learn.task2.date.validator.Validator;

public class DateCreator {

    Validator validator;
    Parser parser;

    public DateCreator() {
        validator = new Validator();
        parser = new Parser();
    }

    public Date createDate(String[] input) throws IncorrectParametersException {
        if (validator.isValid(input)) {
            int[] inputInt = parser.parseStrArrToIntArr(input);
            return new Date(inputInt[0], inputInt[1], inputInt[2]);
        } else {
            throw new IncorrectParametersException();
        }
//        return new Date(intInput[0], intInput[1], intInput[2]);
    }
}
//exception creator выкидывает