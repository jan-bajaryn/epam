package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.service.PaginationService;
import by.epam.cafe.service.ProductService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.PaginationCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductList extends by.epam.cafe.controller.command.Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();

    private final PaginationCalculator paginationCalculator = serviceFactory.getPaginationCalculator();

    private final PaginationService paginationService = serviceFactory.getPaginationService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int part = paginationCalculator.calculatePartParam(request.getParameter("pagination"));

            List<Product> all = productService.findAllByPart((part - 1) * 10, 10);
            request.setAttribute("products", all);
            request.setAttribute("paginationMap", paginationService.calculate(productService.findAll().size(), part, 10));
            request.getRequestDispatcher("/WEB-INF/jsp/admin/product-list.jsp").forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }
    }
}
