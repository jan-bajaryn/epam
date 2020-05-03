package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.service.ProductService;
import by.epam.cafe.service.PutItemService;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PutItemAnon extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(PutItemAnon.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();
    private final PutItemService putItemService = serviceFactory.getPutItemService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        try {

            String referrer = request.getHeader("referer");

            String id = request.getParameter("variant");
            Integer prodId = Integer.valueOf(id);

            HttpSession session = request.getSession();
            Map<Product, Integer> basket = takeBasket(session);

            Product entityById = productService.findEntityById(prodId);

            if (entityById != null) {
                putItemService.putProduct(entityById, basket);
                commitSession(basket, session);

                log.info("referrer = {}", referrer);
                response.sendRedirect(referrer);
            } else {
                //TODO LOGIC
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
            }
        } catch (NumberFormatException e) {
            //TODO LOGIC
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }


    }

    private void commitSession(Map<Product, Integer> basket, HttpSession session) {
        session.setAttribute("basket", basket);
    }

    private Map<Product, Integer> takeBasket(HttpSession session) {
        Map<Product, Integer> basket;
        Object basketObj = session.getAttribute("basket");
        if (basketObj == null) {
            basket = new HashMap<>();
        } else {
            basket = ((Map<Product, Integer>) basketObj);
        }
        return basket;
    }
}
