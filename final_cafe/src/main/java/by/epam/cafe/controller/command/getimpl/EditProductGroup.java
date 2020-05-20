package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.service.db.ProductGroupService;
import by.epam.cafe.service.db.ProductService;
import by.epam.cafe.service.exception.IllegalPathParamException;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.helper.PathVarCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditProductGroup extends by.epam.cafe.controller.command.Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            ProductGroup productGroup = productGroupService.findEntityById(id);

            if (productGroup != null) {
                request.setAttribute("group", productGroup);
                request.setAttribute("types", ProductType.values());

                putProducts(request);

                request.getRequestDispatcher("/WEB-INF/jsp/admin/edit-product-group.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("/WEB-INF/jsp/errors/something_went_wrong.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            request.getRequestDispatcher("/WEB-INF/jsp/errors/something_went_wrong.jsp").forward(request, response);
        }
    }

    private void putProducts(HttpServletRequest request) throws ServiceException {
        List<Product> emptyProducts = productService.findAllByProductGroupNull();
        request.setAttribute("products", emptyProducts);
    }
}
