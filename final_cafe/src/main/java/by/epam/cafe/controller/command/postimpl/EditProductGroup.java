package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.ProductGroupService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.impl.ImageWriterService;
import by.epam.cafe.service.validator.ProductGroupValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductGroup extends by.epam.cafe.controller.command.Command {
    private static final Logger log = LogManager.getLogger(EditProductGroup.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();

    private final ImageWriterService imageWriterService = serviceFactory.getImageWriterService();

    private final ProductGroupValidator productGroupValidator = serviceFactory.getProductGroupValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {

        ProductGroup productGroup = productGroupService.parseRequest(request);

        boolean valid = productGroupValidator.isValid(productGroup);
        log.debug("valid = {}", valid);
        try {
            if (valid && productGroupService.update(productGroup)) {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/admin/product-group-list");
            } else {
                imageWriterService.deleteImageIfNeed(productGroup.getPhotoName());
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
            }
        } catch (NullParamDaoException | ServiceException e) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }

    }

}
