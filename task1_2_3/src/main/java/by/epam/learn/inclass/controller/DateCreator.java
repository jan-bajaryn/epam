package by.epam.learn.inclass.controller;

import by.epam.learn.inclass.domain.Date;
import by.epam.learn.inclass.exception.IncorrectParametersException;
import by.epam.learn.inclass.runner.Parser;
import by.epam.learn.inclass.validator.Validator;

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