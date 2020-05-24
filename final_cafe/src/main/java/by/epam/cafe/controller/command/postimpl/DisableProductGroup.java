package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.service.db.ProductGroupService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisableProductGroup extends by.epam.cafe.controller.command.Command {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        String id = request.getParameter("id");

        try {
            productGroupService.disableById(Integer.valueOf(id));
            return new Redirect("/admin/product-group-list?pagination=1");
        } catch (NumberFormatException | ServiceException e) {
            return new SendError(500);
        }
    }
}
