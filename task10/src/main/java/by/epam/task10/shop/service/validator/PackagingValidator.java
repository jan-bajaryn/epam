package by.epam.task10.shop.service.validator;

import by.epam.task10.shop.entity.Packaging;

public class PackagingValidator {
    public boolean isValid(Packaging packaging) {
        return packaging != null && packaging.getColor() != null && packaging.getSize() != null;
    }
}
