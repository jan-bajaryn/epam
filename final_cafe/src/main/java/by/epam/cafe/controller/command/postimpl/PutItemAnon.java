package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.service.db.ProductService;
import by.epam.cafe.service.helper.PutItemService;
import by.epam.cafe.service.exception.ServiceException;
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

public class PutItemAnon extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(PutItemAnon.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();
    private final PutItemService putItemService = serviceFactory.getPutItemService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        try {

            String referer = request.getHeader("referer");

            String id = request.getParameter("variant");
            Integer prodId = Integer.valueOf(id);

            HttpSession session = request.getSession();
            Map<Product, Integer> basket = takeBasket(session);

            Product entityById = productService.findEntityById(prodId);

            if (entityById != null) {
                putItemService.putProduct(entityById, basket);
                commitSession(basket, session);

                return new Redirect(referer, false);
            }
        } catch (NumberFormatException | ServiceException e) {
            log.debug("e: ", e);
        }
        return new SendError(500);
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
