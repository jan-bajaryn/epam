package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.entity.struct.ValueHolder;
import by.epam.cafe.service.impl.ImageWriterService;
import by.epam.cafe.service.parser.helper.ValidateAndPutter;
import by.epam.cafe.service.parser.parts.*;
import by.epam.cafe.service.parser.parts.IdParser;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public class ProductGroupParser {
    private static final Logger log = LogManager.getLogger(ProductGroupParser.class);
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String DESCRIPTION = "description";
    private static final String PRODUCTS = "products";
    private static final String FILE = "file";
    private static final String ID = "id";
    private static final String DISABLED = "disabled";

    private final ValidateAndPutter validateAndPutter = ValidateAndPutter.getInstance();


    private final ImageWriterService imageWriterService = new ImageWriterService();

    private final ProductGroupNameParser productGroupNameParser = new ProductGroupNameParser();
    private final ProductTypeParser productTypeParser = new ProductTypeParser();
    private final ProductGroupDescriptionParser productGroupDescriptionParser = new ProductGroupDescriptionParser();
    private final IdParser idParser = new IdParser();
    private final PhotoNameParser photoNameParser = new PhotoNameParser();
    private final BooleanParser booleanParser = new BooleanParser();


    public boolean fillFields(ProductGroup productGroup, FileItem part, Map<String, String> redirect, ValueHolder<String> fileNameOpt) {

        File file = null;

        try {
            switch (part.getFieldName()) {
                case NAME:
                    String name = part.getString("UTF-8");
                    Optional<String> nameOpt = productGroupNameParser.parse(name);
                    if (validateAndPutter.validateAndPut(redirect, nameOpt, NAME, name)) {
                        productGroup.setName(nameOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case TYPE:
                    String type = part.getString("UTF-8");
                    Optional<ProductType> typeOpt = productTypeParser.parse(type);
                    if (validateAndPutter.validateAndPut(redirect, typeOpt, TYPE, type)) {
                        productGroup.setType(typeOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case DESCRIPTION:
                    String description = part.getString("UTF-8");
                    log.debug("description = {}", description);
                    Optional<String> descriptionOpt = productGroupDescriptionParser.parse(description);
                    if (validateAndPutter.validateAndPut(redirect, descriptionOpt, DESCRIPTION, description)) {
                        productGroup.setDescription(descriptionOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case PRODUCTS:
                    String product = part.getString("UTF-8");
                    Optional<Integer> productOpt = idParser.parse(product);
                    if (validateAndPutter.validateAndPut(redirect, productOpt, PRODUCTS, product)) {
                        productGroup.getProducts().add(
                                Product.newBuilder().id(productOpt.get()).build()
                        );
                        return true;
                    } else {
                        return false;
                    }
                case FILE:
                    file = imageWriterService.downloadFile(part);
                    String fileName = file.getName();
                    fileNameOpt.setValue(fileName);
                    Optional<String> parse = photoNameParser.parse(fileName);
                    if (validateAndPutter.validateAndPut(redirect, parse, FILE, fileName)) {
                        productGroup.setPhotoName(fileName);
                        return true;
                    } else {
                        return false;
                    }
                case ID:
                    String id = part.getString("UTF-8");
                    Optional<Integer> idOpt = idParser.parse(id);
                    if (validateAndPutter.validateAndPut(redirect, idOpt, ID, id)) {
                        productGroup.setId(idOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case DISABLED:
                    String isDisabled = part.getString("UTF-8");
                    Optional<Boolean> isDisabledOpt = booleanParser.parse(isDisabled);
                    if (validateAndPutter.validateAndPut(redirect, isDisabledOpt, DISABLED, isDisabled)) {
                        productGroup.setDisabled(isDisabledOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                default: {
                    log.error("irregular field");
                    return true;
                }
            }
        } catch (Exception e) {
            if (file != null) {
                // TODO check security
                file.delete();
            }
            return false;
        }

    }
}
