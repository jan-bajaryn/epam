package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.entity.impl.DeliveryInf;
import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class MakeOrderClient extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(MakeOrderClient.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        try {
            Order order = buildOrder(request);
            log.debug("order = {}", order);
            boolean isUpdated = orderService.update(order);

            if (isUpdated) {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/your-order/" + order.getId());
            }

        } catch (ParseException e) {
            log.debug("e: ", e);
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }

    }

    private Order buildOrder(HttpServletRequest request) throws ParseException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        String street = request.getParameter("street");
        String comments = request.getParameter("comments");
        String floor = request.getParameter("floor");
        String porch = request.getParameter("porch");
        String room = request.getParameter("room");
        String house = request.getParameter("house");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String time = request.getParameter("time");

        LocalDateTime dateTime = parseToLocalDateTime(time);


        log.debug("dateTime = {}", dateTime);
        DeliveryInf deliveryInf = DeliveryInf.newBuilder()
                .porch(Integer.valueOf(porch))
                .deliveryTime(dateTime)
                .comments(comments)
                .email(email)
                .phone(tel)
                .floor(Integer.valueOf(floor))
                .house(house)
                .room(room)
                .street(street)
                .build();

        Order order = orderService.findOrCreateCurrentByUserId(user.getId());
        order.setCreation(LocalDateTime.now());
        order.setClientName(name);
        order.setPaymentType(PaymentType.CASH);
        order.setDeliveryInf(deliveryInf);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setPrice(calcSum(order.getProducts()));
        order.setUser(user);

        return order;
    }

    private LocalDateTime parseToLocalDateTime(String time) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        Date parse = dateFormat.parse(time);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(parse);

        return LocalDateTime.now().withHour(calendar.get(Calendar.HOUR_OF_DAY))
                .withMinute(calendar.get(Calendar.MINUTE))
                .withSecond(0);
    }

    private Integer calcSum(Map<Product, Integer> basket) {
        return basket.entrySet().stream()
                .map(e -> e.getKey().getPrice() * e.getValue())
                .reduce(0, Integer::sum);
    }

}
