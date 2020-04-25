package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.entity.impl.DeliveryInf;
import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.service.DeliveryInfService;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditOrderCommand extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(EditOrderCommand.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        try {

            Order order = findOrder(request);
            buildDeliveryInf(order, request);

            boolean update = orderService.update(order);
            if (update) {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/order-list");
            } else {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
            }
        } catch (ParseException | IllegalArgumentException e) {
            log.debug("e: ", e);
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }


    }

    private void buildDeliveryInf(Order order, HttpServletRequest request) throws ParseException {
        DeliveryInf deliveryInf = order.getDeliveryInf();

        String timeSt = request.getParameter("time");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String room = request.getParameter("room");
        String porchSt = request.getParameter("porch");
        String floorSt = request.getParameter("floor");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String comments = request.getParameter("comments");
        Integer porch = Integer.valueOf(porchSt);
        Integer floor = Integer.valueOf(floorSt);
        LocalDateTime time = parseToLocalDateTime(timeSt);

        deliveryInf.setPorch(porch);
        deliveryInf.setDeliveryTime(time);
        deliveryInf.setComments(comments);
        deliveryInf.setEmail(email);
        deliveryInf.setPhone(tel);
        deliveryInf.setFloor(floor);
        deliveryInf.setHouse(house);
        deliveryInf.setRoom(room);
        deliveryInf.setStreet(street);

    }

    private Order findOrder(HttpServletRequest request) {
        String idSt = request.getParameter("id");
        String statusSt = request.getParameter("status");
        String paymentTypeSt = request.getParameter("payment_type");
        String priceSt = request.getParameter("price");
        String name = request.getParameter("name");

        Integer id = Integer.valueOf(idSt);
        Integer price = Integer.valueOf(priceSt);

        OrderStatus status = OrderStatus.valueOf(statusSt);
        PaymentType type = PaymentType.valueOf(paymentTypeSt);


        Order order = orderService.findEntityById(id);

        order.setClientName(name);
        order.setPaymentType(type);
        order.setStatus(status);
        order.setPrice(price);
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
//        return LocalDateTime.now().withHour(parse.getHours())
//                .withMinute(parse.getMinutes())
//                .withSecond(0);
    }
}
