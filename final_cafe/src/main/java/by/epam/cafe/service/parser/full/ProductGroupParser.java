package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.entity.struct.ValueHolder;
import org.apache.commons.fileupload.FileItem;

import java.util.Map;

/**
 * Dedicated to parse input parameters into {@link ProductGroup},
 * Check and return validity of input parameters
 */
public interface ProductGroupParser {

    /**
     * name of parameter {@link ProductGroup#getName()}
     */
    String NAME = "name";
    /**
     * name of parameter {@link ProductGroup#getType()}}
     */
    String TYPE = "type";
    /**
     * name of parameter {@link ProductGroup#getDescription()}}
     */
    String DESCRIPTION = "description";

    /**
     * name of parameter {@link ProductGroup#getProducts()}
     */
    String PRODUCTS = "products";
    /**
     * name of parameter of transferring file
     * In entity save to {@link ProductGroup#getPhotoName()}
     */
    String FILE = "file";
    /**
     * name of parameter {@link ProductGroup#getId()}
     */
    String ID = "id";

    /**
     * name of parameter {@link ProductGroup#isDisabled()}
     */
    String DISABLED = "disabled";


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
    boolean fillFields(ProductGroup productGroup, FileItem part, Map<String, String> redirect, ValueHolder<String> fileNameOpt);
}
