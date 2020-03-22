package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.ProductGroupService;
import by.epam.cafe.service.ProductService;
import by.epam.cafe.service.exception.NullServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IndexCommand extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(IndexCommand.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();
    private final ProductService productService = serviceFactory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductType prodType = calcVariables(request);
        log.info("execute: prodType = {}", prodType);
        try {
            List<ProductGroup> list = productGroupService.findAllByProductTypeNotDisabled(prodType);
            Map<ProductGroup, Integer> result = list.stream()
                    .collect(Collectors.toMap(p -> p, this::getMinPrice));
            request.setAttribute("products", result);
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        } catch (NullServiceException e) {
            log.error("Error:", e);
        }

    }

    private Integer getMinPrice(ProductGroup p) {
        return p.getProducts().stream()
                .map(Product::getPrice)
                .min(Integer::compareTo)
                .orElse(0);
    }

    private ProductType calcVariables(HttpServletRequest request) {
        String type = request.getParameter("type");
        ProductType prodType;
        try {
            prodType = ProductType.valueOf(type);
        } catch (IllegalArgumentException | NullPointerException e) {
            log.info("Parameter exception: ", e);
            prodType = ProductType.PIZZA;
        }
        return prodType;
    }
}
