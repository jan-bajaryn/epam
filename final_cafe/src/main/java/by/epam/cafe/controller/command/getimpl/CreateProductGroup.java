package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Forward;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.service.db.ProductService;
import by.epam.cafe.service.exception.ServiceException;
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
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> emptyProd = productService.findAllByProductGroupNull();
            request.setAttribute("products", emptyProd);
            request.setAttribute("types", ProductType.values());
            return new Forward("/admin/create-product-group.jsp");
        } catch (ServiceException e) {
            return new SendError(500);
        }
    }
}
