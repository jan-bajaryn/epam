package by.epam.task10.shop.service.validator;

import by.epam.task10.shop.entity.Sweet;

public class SweetValidator {
    public boolean isValid(Sweet sweet) {
        if (sweet == null || sweet.getName() == null || !sweet.getName().matches("[^\n]+")) {
            return false;
        }
        return sweet.getCount() >= 0;
    }
}
