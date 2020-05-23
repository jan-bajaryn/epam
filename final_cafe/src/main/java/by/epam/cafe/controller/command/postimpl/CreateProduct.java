package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.service.db.ProductService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.full.ProductParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.epam.cafe.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class CreateProduct extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(CreateProduct.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();

    private final ProductParser productParser = serviceFactory.getProductParser();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {

        String referer = request.getHeader("referer");


        log.debug("begin method");

        Map<String, String> redirect = new HashMap<>();
        Product build = validateAndTakeParams(request, redirect);

        if (build != null) {

            try {
                if (productService.create(build) != null) {
                    response.sendRedirect(request.getContextPath() + request.getServletPath() + "/admin/product-list?pagination=1");
                } else {
                    request.setAttribute("unknown_error", "true");
                    response.sendRedirect(referer);
                }
            } catch (ServiceException e) {
                request.setAttribute("unknown_error", "true");
                response.sendRedirect(referer);
            }
        } else {
            request.getSession().setAttribute(REDIRECTED_INFO, redirect);
            response.sendRedirect(referer);
        }

    }


    private Product validateAndTakeParams(HttpServletRequest request, Map<String, String> redirect) {
        String productGroup = request.getParameter("product_group");
        String price = request.getParameter("price");
        String weight = request.getParameter("weight");

        return productParser.parseProduct(redirect, productGroup, price, weight);
    }
}
