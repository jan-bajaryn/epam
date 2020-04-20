package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.ProductGroupService;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.impl.ImageWriterService;
import by.epam.cafe.service.validator.ProductGroupValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateProductGroupCommand extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(CreateProductGroupCommand.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();

    private final ImageWriterService imageWriterService = serviceFactory.getImageWriterService();

    private final ProductGroupValidator productGroupValidator = serviceFactory.getProductGroupValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {

        ProductGroup productGroup = productGroupService.parseRequest(request);

        boolean withoutId = productGroupValidator.validWithoutId(productGroup);
        log.debug("withoutId = {}", withoutId);
        if (withoutId && productGroupService.create(productGroup) != null) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/admin/product-group-list");
        } else {
            imageWriterService.deleteImageIfNeed(productGroup.getPhotoName());
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }

    }

}
