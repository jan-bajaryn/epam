package by.epam.inclassactivity.controller;


import by.epam.inclassactivity.domain.Date;
import by.epam.inclassactivity.exception.IncorrectParametersException;
import by.epam.inclassactivity.runner.Parser;
import by.epam.inclassactivity.validator.Validator;

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