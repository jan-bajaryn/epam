package by.epam.cafe.service.parser.full.impl;

import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.entity.struct.OptionalNullable;
import by.epam.cafe.entity.struct.ValueHolder;
import by.epam.cafe.service.helper.ImageWriterService;
import by.epam.cafe.service.helper.impl.ImageWriterServiceImpl;
import by.epam.cafe.service.parser.helper.ValidateAndPutter;
import by.epam.cafe.service.parser.helper.impl.ValidateAndPutterImpl;
import by.epam.cafe.service.parser.parts.impl.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;

public class ProductGroupParserImpl implements by.epam.cafe.service.parser.full.ProductGroupParser {
    private static final Logger log = LogManager.getLogger(ProductGroupParserImpl.class);

    private final ValidateAndPutter validateAndPutter = ValidateAndPutterImpl.getInstance();


    private final ImageWriterService imageWriterService = ImageWriterServiceImpl.getInstance();

    private final ProductGroupNameParser productGroupNameParser = ProductGroupNameParser.getInstance();
    private final ProductTypeParser productTypeParser = ProductTypeParser.getInstance();
    private final ProductGroupDescriptionParser productGroupDescriptionParser = ProductGroupDescriptionParser.getInstance();
    private final IdParser idParser = IdParser.getInstance();
    private final PhotoNameParser photoNameParser = PhotoNameParser.getInstance();
    private final BooleanParser booleanParser = BooleanParser.getInstance();


    /**
     * Take part and choose what operation should be done with it and
     * is input valid. If valid, service should put the value in entity, otherwise
     * return false.
     * If service save the file, it put the value in fileNameOpt to track it
     *
     * @param productGroup entity in what should be put a value from part
     * @param part         FileItem from multipart form data
     * @param redirect     Map to return what parameter is valid, and value with
     *                     what parameter was in input
     *                     First String in the map is the name of parameter
     *                     Second String in the map is value of input in parameter
     *                     or information about existing error in the map
     *                     For example {street, abcde} means that input for
     *                     parameter of name "street" was "abcde"
     *                     {street_error, true} means that in parameter
     *                     of name "street" was error.
     * @param fileNameOpt  if file will be downloaded the value should be put there
     * @return true if parameter successfully parsed, and otherwise returns false
     */
    @Override
    public boolean fillFieldsOnCreate(ProductGroup productGroup, FileItem part, Map<String, String> redirect, ValueHolder<String> fileNameOpt) {

        File file = null;

        try {
            switch (part.getFieldName()) {
                case NAME:
                    String name = part.getString("UTF-8");
                    OptionalNullable<String> nameOpt = productGroupNameParser.parse(name);
                    if (validateAndPutter.validateAndPut(redirect, nameOpt, NAME, name)) {
                        productGroup.setName(nameOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case TYPE:
                    String type = part.getString("UTF-8");
                    OptionalNullable<ProductType> typeOpt = productTypeParser.parse(type);
                    if (validateAndPutter.validateAndPut(redirect, typeOpt, TYPE, type)) {
                        productGroup.setType(typeOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case DESCRIPTION:
                    String description = part.getString("UTF-8");
                    log.debug("description = {}", description);
                    OptionalNullable<String> descriptionOpt = productGroupDescriptionParser.parse(description);
                    if (validateAndPutter.validateAndPut(redirect, descriptionOpt, DESCRIPTION, description)) {
                        productGroup.setDescription(descriptionOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case PRODUCTS:
                    String product = part.getString("UTF-8");
                    OptionalNullable<Integer> productOpt = idParser.parse(product);
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
                    OptionalNullable<String> parse = photoNameParser.parse(fileName);
                    if (validateAndPutter.validateAndPut(redirect, parse, FILE, fileName)) {
                        productGroup.setPhotoName(fileName);
                        return true;
                    }
                    return false;
                case ID:
                    String id = part.getString("UTF-8");
                    OptionalNullable<Integer> idOpt = idParser.parse(id);
                    if (validateAndPutter.validateAndPut(redirect, idOpt, ID, id)) {
                        productGroup.setId(idOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case DISABLED:
                    String isDisabled = part.getString("UTF-8");
                    OptionalNullable<Boolean> isDisabledOpt = booleanParser.parse(isDisabled);
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
                file.delete();
            }
            return false;
        }

    }

    /**
     * Take part and choose what operation should be done with it and
     * is input valid. If valid, service should put the value in entity, otherwise
     * return false.
     * If service save the file, it put the value in fileNameOpt to track it
     *
     * @param productGroup entity in what should be put a value from part
     * @param part         FileItem from multipart form data
     * @param redirect     Map to return what parameter is valid, and value with
     *                     what parameter was in input
     *                     First String in the map is the name of parameter
     *                     Second String in the map is value of input in parameter
     *                     or information about existing error in the map
     *                     For example {street, abcde} means that input for
     *                     parameter of name "street" was "abcde"
     *                     {street_error, true} means that in parameter
     *                     of name "street" was error.
     * @param fileNameOpt  if file will be downloaded the value should be put there
     * @return true if parameter successfully parsed, and otherwise returns false
     */
    @Override
    public boolean fillFieldsOnUpdate(ProductGroup productGroup, FileItem part, Map<String, String> redirect, ValueHolder<String> fileNameOpt) {

        File file = null;

        try {
            switch (part.getFieldName()) {
                case NAME:
                    String name = part.getString("UTF-8");
                    OptionalNullable<String> nameOpt = productGroupNameParser.parse(name);
                    if (validateAndPutter.validateAndPut(redirect, nameOpt, NAME, name)) {
                        productGroup.setName(nameOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case TYPE:
                    String type = part.getString("UTF-8");
                    OptionalNullable<ProductType> typeOpt = productTypeParser.parse(type);
                    if (validateAndPutter.validateAndPut(redirect, typeOpt, TYPE, type)) {
                        productGroup.setType(typeOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case DESCRIPTION:
                    String description = part.getString("UTF-8");
                    log.debug("description = {}", description);
                    OptionalNullable<String> descriptionOpt = productGroupDescriptionParser.parse(description);
                    if (validateAndPutter.validateAndPut(redirect, descriptionOpt, DESCRIPTION, description)) {
                        productGroup.setDescription(descriptionOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case PRODUCTS:
                    String product = part.getString("UTF-8");
                    OptionalNullable<Integer> productOpt = idParser.parse(product);
                    if (validateAndPutter.validateAndPut(redirect, productOpt, PRODUCTS, product)) {
                        productGroup.getProducts().add(
                                Product.newBuilder().id(productOpt.get()).build()
                        );
                        return true;
                    } else {
                        return false;
                    }
                case FILE:
                    log.debug("part.getSize() = {}", part.getSize());
                    if (part.getSize() != 0) {
                        file = imageWriterService.downloadFile(part);
                        String fileName = file.getName();
                        fileNameOpt.setValue(fileName);
                        OptionalNullable<String> parse = photoNameParser.parse(fileName);
                        if (validateAndPutter.validateAndPut(redirect, parse, FILE, fileName)) {
                            productGroup.setPhotoName(fileName);
                            return true;
                        }
                        return false;
                    } else {
                        OptionalNullable<String> parse = OptionalNullable.of(null);
                        return validateAndPutter.validateAndPut(redirect, parse, FILE, null);
                    }
                case ID:
                    String id = part.getString("UTF-8");
                    OptionalNullable<Integer> idOpt = idParser.parse(id);
                    if (validateAndPutter.validateAndPut(redirect, idOpt, ID, id)) {
                        productGroup.setId(idOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case DISABLED:
                    String isDisabled = part.getString("UTF-8");
                    OptionalNullable<Boolean> isDisabledOpt = booleanParser.parse(isDisabled);
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
                file.delete();
            }
            return false;
        }

    }
}
