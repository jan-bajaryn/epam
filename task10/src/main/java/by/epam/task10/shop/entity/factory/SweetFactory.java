package by.epam.task10.shop.entity.factory;

import by.epam.task10.shop.entity.Sweet;
import by.epam.task10.shop.service.validator.SweetValidator;

public class SweetFactory {
    private SweetValidator sweetValidator = new SweetValidator();

    public Sweet create(String name, int size, int count) throws IllegalFactParamSweetException {
        Sweet sweet = new Sweet(name.strip(), size, count);
        if (sweetValidator.isValid(sweet)){
            return sweet;
        } else {
            throw new IllegalFactParamSweetException();
        }
    }
}
