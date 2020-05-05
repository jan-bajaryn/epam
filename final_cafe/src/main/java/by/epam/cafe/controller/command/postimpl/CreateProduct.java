package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.ProductService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.validator.ProductValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateProduct extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(CreateProduct.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();

    private final ProductValidator productValidator = serviceFactory.getProductValidator();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        log.debug("begin method");

        try {
            Product product = buildProduct(request);

            if (productValidator.validWithoutId(product) && productService.create(product)!=null) {
                log.debug("success");
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/admin/product-list");
            } else {
                log.debug("fail");
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
            }
        } catch (NumberFormatException | NullPointerException | ServiceException e) {
            log.debug("e: ", e);
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }

    }

    private Product buildProduct(HttpServletRequest request) {
        String productGroup = request.getParameter("product_group");
        String price = request.getParameter("price");
        String weight = request.getParameter("weight");

        return Product.newBuilder()
                .price(Integer.valueOf(price))
                .weight(Integer.valueOf(weight))
                .productGroup(
                        productGroup == null || productGroup.isEmpty() ?
                                null :
                                ProductGroup.newBuilder().id(Integer.valueOf(productGroup)).build()
                )
                .build();
    }
}
