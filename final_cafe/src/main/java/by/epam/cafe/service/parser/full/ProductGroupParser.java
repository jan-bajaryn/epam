package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.impl.ImageWriterService;
import by.epam.cafe.service.parser.helper.ValidateAndPutter;
import by.epam.cafe.service.parser.parts.*;
import by.epam.cafe.service.parser.parts.impl.IdParser;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public class ProductGroupParser {
    private static final Logger log = LogManager.getLogger(ProductGroupParser.class);

    private final ValidateAndPutter validateAndPutter = ValidateAndPutter.getInstance();


    private final ImageWriterService imageWriterService = new ImageWriterService();

    private final ProductGroupNameParser productGroupNameParser = new ProductGroupNameParser();
    private final ProductTypeParser productTypeParser = new ProductTypeParser();
    private final ProductGroupDescriptionParser productGroupDescriptionParser = new ProductGroupDescriptionParser();
    private final IdParser idParser = new IdParser();
    private final PhotoNameParser photoNameParser = new PhotoNameParser();
    private final DisabledParser disabledParser = new DisabledParser();


    public boolean fillFields(ProductGroup productGroup, FileItem part, Map<String, String> redirect) {

        File file = null;

        try {
            switch (part.getFieldName()) {
                case "name":
                    String name = part.getString();
                    Optional<String> nameOpt = productGroupNameParser.parse(name);
                    if (validateAndPutter.validateAndPut(redirect, nameOpt, "name", name)) {
                        productGroup.setName(nameOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case "type":
                    String type = part.getString();
                    Optional<ProductType> typeOpt = productTypeParser.parse(type);
                    if (validateAndPutter.validateAndPut(redirect, typeOpt, "type", type)) {
                        productGroup.setType(typeOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case "description":
                    String description = part.getString();
                    Optional<String> descriptionOpt = productGroupDescriptionParser.parse(description);
                    if (validateAndPutter.validateAndPut(redirect, descriptionOpt, "description", description)) {
                        productGroup.setDescription(descriptionOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case "products":
                    String product = part.getString();
                    Optional<Integer> productOpt = idParser.parse(product);
                    if (validateAndPutter.validateAndPut(redirect, productOpt, "products", product)) {
                        productGroup.getProducts().add(
                                Product.newBuilder().id(productOpt.get()).build()
                        );
                        return true;
                    } else {
                        return false;
                    }
                case "file":
                    file = imageWriterService.downloadFile(part);
                    String fileName = file.getName();
                    Optional<String> parse = photoNameParser.parse(fileName);
                    if (validateAndPutter.validateAndPut(redirect, parse, "file", fileName)) {
                        productGroup.setPhotoName(fileName);
                        return true;
                    } else {
                        return false;
                    }
                case "id":
                    String id = part.getString();
                    Optional<Integer> idOpt = idParser.parse(id);
                    if (validateAndPutter.validateAndPut(redirect, idOpt, "id", id)) {
                        productGroup.setId(idOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case "disabled":
                    String isDisabled = part.getString();
                    Optional<Boolean> isDisabledOpt = disabledParser.parse(isDisabled);
                    if (validateAndPutter.validateAndPut(redirect, isDisabledOpt, "disabled", isDisabled)) {
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
