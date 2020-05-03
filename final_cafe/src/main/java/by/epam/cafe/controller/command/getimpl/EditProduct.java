package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.ProductGroupService;
import by.epam.cafe.service.ProductService;
import by.epam.cafe.service.exception.IllegalPathParamException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.PathVarCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditProduct extends by.epam.cafe.controller.command.Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final ProductService productService = serviceFactory.getProductService();
    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            Product product = productService.findEntityById(id);

            if (product != null) {
                request.setAttribute("product", product);

                List<ProductGroup> allExcept = productGroupService.findAllExcept(product.getProductGroup());

                request.setAttribute("groups", allExcept);

                request.getRequestDispatcher("/WEB-INF/jsp/admin/edit-product.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("/WEB-INF/jsp/errors/something_went_wrong.jsp").forward(request, response);
            }
        } catch (IllegalPathParamException | NullParamDaoException e) {
            request.getRequestDispatcher("/WEB-INF/jsp/errors/something_went_wrong.jsp").forward(request, response);
        }
    }
}
