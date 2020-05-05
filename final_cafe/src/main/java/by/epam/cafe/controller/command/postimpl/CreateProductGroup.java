package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.ProductGroupService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.impl.ImageWriterService;
import by.epam.cafe.service.parser.full.ProductGroupParser;
import by.epam.cafe.service.validator.ProductGroupValidator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateProductGroup extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(CreateProductGroup.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();
    private final ImageWriterService imageWriterService = serviceFactory.getImageWriterService();
    private final ProductGroupValidator productGroupValidator = serviceFactory.getProductGroupValidator();
    private static final DiskFileItemFactory FILE_ITEM_FACTORY = new DiskFileItemFactory();
    private final ProductGroupParser productGroupParser = serviceFactory.getProductGroupParser();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {

        try {

            Map<String, String> redirect = new HashMap<>();

            ProductGroup productGroup = parseRequest(request, redirect);

            boolean withoutId = productGroupValidator.validWithoutId(productGroup);
            log.debug("withoutId = {}", withoutId);
            if (withoutId && productGroupService.create(productGroup) != null) {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/admin/product-group-list");
            } else {
                imageWriterService.deleteImageIfNeed(productGroup.getPhotoName());
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
            }
        } catch (ServiceException e) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }

    }

    public ProductGroup parseRequest(HttpServletRequest request, Map<String, String> redirect) {
        try {

            ProductGroup productGroup = new ProductGroup();

            ServletFileUpload fileUpload = new ServletFileUpload(FILE_ITEM_FACTORY);
            List<FileItem> parts = fileUpload.parseRequest(request);

            for (FileItem part : parts) {

                productGroupParser.fillFields(productGroup, part, redirect);
            }
            return productGroup;
        } catch (FileUploadException e) {
            log.error("e: ", e);
            return null;
        }
    }

}
