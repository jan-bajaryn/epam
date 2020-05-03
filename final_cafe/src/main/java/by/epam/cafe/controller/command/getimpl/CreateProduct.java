package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.ProductGroupService;
import by.epam.cafe.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateProduct extends by.epam.cafe.controller.command.Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<ProductGroup> all = productGroupService.findAll();

            request.setAttribute("groups", all);

            request.getRequestDispatcher("/WEB-INF/jsp/admin/create-product.jsp").forward(request, response);

        } catch (NullParamDaoException e) {
            request.getRequestDispatcher("/WEB-INF/jsp/errors/something_went_wrong.jsp").forward(request, response);
        }

    }
}
