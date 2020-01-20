package by.epam.task10.shop.parser;

import by.epam.task10.shop.entity.Sweet;
import by.epam.task10.shop.entity.factory.exception.IllegalFactParamSweetException;
import by.epam.task10.shop.entity.factory.SweetFactory;
import by.epam.task10.shop.parser.exception.WrongSweetParamsCountException;

import static by.epam.task10.shop.service.FilesItemsBringer.SWEET_PARAMS_COUNT;


public class SweetParser {
    private SweetFactory sweetFactory = new SweetFactory();

    public Sweet parseStringToSweet(String[] sweetParams) throws WrongSweetParamsCountException {
        if (sweetParams == null || sweetParams.length != SWEET_PARAMS_COUNT) {
            throw new WrongSweetParamsCountException();
        }
        try {
            String name = sweetParams[0];
            int size = Integer.parseInt(sweetParams[1]);
            int count = Integer.parseInt(sweetParams[2]);
            return sweetFactory.create(name, count);
        } catch (NumberFormatException | IllegalFactParamSweetException e) {
            throw new WrongSweetParamsCountException(e);
        }


    }
}
