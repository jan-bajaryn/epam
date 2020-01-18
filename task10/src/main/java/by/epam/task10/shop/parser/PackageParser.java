package by.epam.task10.shop.parser;

import by.epam.task10.shop.entity.Packaging;
import by.epam.task10.shop.entity.factory.IllegalParamsFactPackagingException;
import by.epam.task10.shop.entity.factory.PackagingFactory;

import static by.epam.task10.shop.service.ItemsBringer.PACK_PARAMS_COUNT;

public class PackageParser {

    private PackagingFactory packagingFactory = new PackagingFactory();

    public Packaging parseStringToPackaging(String[] s) throws IllegalParamsPackingException {
        if (s == null || s.length != PACK_PARAMS_COUNT) {
            throw new IllegalParamsPackingException();
        }
        String size = s[0];
        String color = s[1];
        try {
            Packaging.PackageSize packageSize = Packaging.PackageSize.valueOf(size);
            Packaging.PackageColor packageColor = Packaging.PackageColor.valueOf(color);
            return packagingFactory.create(packageSize, packageColor);
        } catch (IllegalArgumentException | IllegalParamsFactPackagingException e) {
            throw new IllegalParamsPackingException(e);
        }

    }
}
