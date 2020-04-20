package by.epam.cafe.service.validator;

import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.regex.Pattern;

public class ProductGroupValidator {

    private static final Logger log = LogManager.getLogger(ProductGroupValidator.class);
    private static final String PHOTO_NAME_REGEX = "[-\\w\\d\\s().]{1,30}(\\.png|\\.jpg|\\.jpeg|\\.gif)";
    private static final String NAME_REGEX = "[\\w\\s\\d]{1,30}";
    private static final String DESCR_REGEX = "[\\w\\s\\d]{1,200}";


    public boolean isValid(ProductGroup productGroup) {
        return validWithoutId(productGroup) && productGroup.getId() != null;
    }

    public boolean validWithoutId(ProductGroup productGroup) {

        if (productGroup == null) {
            return false;
        }
        log.debug("---fst---");
        List<Product> products = productGroup.getProducts();

        if (checkProducts(products)) {
            return false;
        }
        log.debug("---sec---");

        boolean photoNullAndRegex = productGroup.getPhotoName() != null &&
                Pattern.compile(PHOTO_NAME_REGEX).matcher(productGroup.getPhotoName()).matches();
        boolean nameNullAndRegex =
                productGroup.getName() != null &&
                        Pattern.compile(NAME_REGEX, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE).matcher(productGroup.getName()).matches();
        boolean descriptionNullAndRegex =
                productGroup.getDescription() != null &&
                        Pattern.compile(DESCR_REGEX).matcher(productGroup.getDescription()).matches();

        boolean prodTypeNull = productGroup.getType() != null;

        logAll(photoNullAndRegex, nameNullAndRegex, descriptionNullAndRegex, prodTypeNull);

        return boolSum(photoNullAndRegex, nameNullAndRegex, descriptionNullAndRegex, prodTypeNull);

    }

    private void logAll(boolean photoRegex, boolean nameNullAndRegex, boolean descriptionNullAndRegex, boolean prodTypeNull) {
        log.debug("photoRegex = {}", photoRegex);
        log.debug("nameNullAndRegex = {}", nameNullAndRegex);
        log.debug("descriptionNullAndRegex = {}", descriptionNullAndRegex);
        log.debug("prodTypeNull = {}", prodTypeNull);
    }

    private boolean checkProducts(List<Product> products) {
        if (products == null) {
            return true;
        }
        for (Product product : products) {
            if (product == null || product.getId() == null) {
                return true;
            }
        }
        return false;
    }

    private boolean boolSum(boolean... booleans) {
        for (boolean ans : booleans) {
            if (!ans) {
                return false;
            }
        }
        return true;
    }
}