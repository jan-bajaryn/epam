package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.ProductGroupService;
import by.epam.cafe.service.ProductService;
import by.epam.cafe.service.exception.IllegalPathParamException;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.PathVarCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddProducts extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(AddProducts.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final ProductService productService = serviceFactory.getProductService();
//    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();
    private final OrderService orderService = serviceFactory.getOrderService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        try {
            Integer pathVar = pathVarCalculator.findLastInteger(request.getPathInfo());
            Order order = orderService.findEntityById(pathVar);

            Map<Product, Integer> products = order.getProducts();

            Comparator<Map.Entry<Product,Integer>> fstComparator = Comparator.comparing(e -> e.getKey().getProductGroup().getId());
            Comparator<Map.Entry<Product,Integer>> sec = Comparator.comparing(e -> e.getKey().getWeight());
            Comparator<Map.Entry<Product,Integer>> fin = fstComparator.thenComparing(sec);

            List<Map.Entry<Product,Integer>> all = productService.findAll().stream()
                    .filter(p -> p.getProductGroup()!=null)
                    .filter(p -> {
                        for (Product product : products.keySet()) {
                            if (product.getId().equals(p.getId())) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .map(p ->Map.entry(p,0))
                    .sorted(fin)
                    .collect(Collectors.toList());

            log.debug("products = {}", products);
            log.debug("all = {}", all);
            request.setAttribute("contain_products", products);
            request.setAttribute("not_contain_products", all);
            request.setAttribute("sum", order.getPrice());
            request.setAttribute("id", order.getId());

            log.debug("all = {}", all);
            request.getRequestDispatcher("/WEB-INF/jsp/add-products.jsp").forward(request, response);
        } catch (IllegalPathParamException | ServiceException e) {
            e.printStackTrace();
        }
    }
}
