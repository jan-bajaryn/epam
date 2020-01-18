package by.epam.task10.shop.entity.factory;

import by.epam.task10.shop.entity.Packaging;
import by.epam.task10.shop.service.validator.PackagingValidator;

public class PackagingFactory {

    private PackagingValidator packagingValidator = new PackagingValidator();

    public Packaging create(Packaging.PackageSize size, Packaging.PackageColor color) throws IllegalParamsFactPackagingException {
        Packaging packaging = new Packaging(size, color);
        if (packagingValidator.isValid(packaging)) {
            return packaging;
        } else {
            throw new IllegalParamsFactPackagingException();
        }
    }
}
