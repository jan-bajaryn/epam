package by.epam.task13.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class OrdersValidator {

    private static final Logger log = LogManager.getLogger(OrdersValidator.class);


    public boolean isValid(String path) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String schemaName = "D:\\Programming\\epam\\epam\\task13\\src\\main\\resources\\orders.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(path);
            validator.validate(source);
            System.out.println(path + " is valid.");
            return true;
        } catch (SAXException e) {
            log.info("validation " + path + " is not valid because "
                    + e.getMessage());
            return false;
        } catch (IOException e) {
            log.info(path + " is not valid because "
                    + e.getMessage());
            return false;
        }
    }

}
