package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.impl.ImageWriterService;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;

public class ProductGroupParser {
    private static final Logger log = LogManager.getLogger(ProductGroupParser.class);


    private final ImageWriterService imageWriterService = new ImageWriterService();


    public void fillFields(ProductGroup productGroup, FileItem part) {

        File file = null;

        try {
            switch (part.getFieldName()) {
                case "name":
                    productGroup.setName(part.getString());
                    break;
                case "type":
                    productGroup.setType(ProductType.valueOf(part.getString()));
                    break;
                case "description":
                    productGroup.setDescription(part.getString());
                    break;
                case "products":
                    productGroup.getProducts().add(
                            Product.newBuilder().id(Integer.valueOf(part.getString())).build()
                    );
                    break;
                case "file":
                    file = imageWriterService.downloadFile(part);
                    productGroup.setPhotoName(file.getName());
                    break;
                case "id":
                    productGroup.setId(Integer.valueOf(part.getString()));
                    break;
                case "disabled":
                    // TODO check that
                    productGroup.setDisabled(part.getString().equals("1"));
                    break;
                default:
                    log.error("irregular field");
            }
        } catch (Exception e) {
            if (file != null) {
                // TODO check security
                file.delete();
            }
        }

    }
}
