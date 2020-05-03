package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.service.ProductService;
import by.epam.cafe.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateProductGroup extends by.epam.cafe.controller.command.Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> emptyProd = productService.findAllByProductGroupNull();
        request.setAttribute("products", emptyProd);
        request.setAttribute("types", ProductType.values());
        request.getRequestDispatcher("/WEB-INF/jsp/admin/create-product-group.jsp").forward(request, response);
    }
}
