package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.UserService;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.NullIfEmptyService;
import by.epam.cafe.service.parser.parts.impl.*;
import by.epam.cafe.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.epam.cafe.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class CreateUser extends by.epam.cafe.controller.command.Command {




    private static final Logger log = LogManager.getLogger(CreateUser.class);
    private static final String POSTFIX = "_error";
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();
    private final UserValidator userValidator = serviceFactory.getUserValidator();

    private final NullIfEmptyService nullEmpt = serviceFactory.getNullIfEmptyService();

    private final EmailParser emailParser = serviceFactory.getEmailParser();
    private final FloorParser floorParser = serviceFactory.getFloorParser();
    private final HouseParser houseParser = serviceFactory.getHouseParser();
    private final NameParser nameParser = serviceFactory.getNameParser();
    private final PasswordParser passwordParser = serviceFactory.getPasswordParser();
    private final PhoneParser phoneParser = serviceFactory.getPhoneParser();
    private final PorchParser porchParser = serviceFactory.getPorchParser();
    private final RoleParser roleParser = serviceFactory.getRoleParser();
    private final RoomParser roomParser = serviceFactory.getRoomParser();
    private final StreetParser streetParser = serviceFactory.getStreetParser();
    private final UsernameParser usernameParser = serviceFactory.getUsernameParser();
    private final SurnameParser surnameParser = serviceFactory.getSurnameParser();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
            String referrer = request.getHeader("referer");

            User build = validateAndTakeParams(request);

            if (build != null) {
                if (userService.create(build) != null) {
                    response.sendRedirect(request.getContextPath() + request.getServletPath() + "/admin/user-list");
                } else {
                    request.setAttribute("unknown_error", "true");
                    response.sendRedirect(referrer);
                }
            } else {
                response.sendRedirect(referrer);
            }

    }

    private User validateAndTakeParams(HttpServletRequest req) {

        Map<String, String> redirect = new HashMap<>();

        String usernameParam = req.getParameter("username");
        String passwordParam = req.getParameter("password");
        String roleParam = req.getParameter("role");
        String nameParam = req.getParameter("name");
        String surnameParam = req.getParameter("surname");
        String houseParam = req.getParameter("house");
        String roomParam = req.getParameter("room");
        String porchParam = req.getParameter("porch");
        String floorParam = req.getParameter("floor");
        String phoneParam = req.getParameter("phone");
        String emailParam = req.getParameter("email");
        String streetParam = req.getParameter("street");

        Optional<String> username = usernameParser.parse(usernameParam);
        Optional<String> password = passwordParser.parse(passwordParam);
        Optional<Role> role = roleParser.parse(roleParam);
        Optional<String> name = nameParser.parse(nameParam);
        Optional<String> surname = surnameParser.parse(surnameParam);
        Optional<String> house = houseParser.parse(houseParam);
        Optional<String> room = roomParser.parse(roomParam);
        Optional<Integer> porch = porchParser.parse(porchParam);
        Optional<Integer> floor = floorParser.parse(floorParam);
        Optional<String> phone = phoneParser.parse(phoneParam);
        Optional<String> email = emailParser.parse(emailParam);
        Optional<String> street = streetParser.parse(streetParam);

        boolean result = validateAndPut(redirect, username, "username", usernameParam) &
                validateAndPut(redirect, password, "password", passwordParam) &
                validateAndPut(redirect, role, "role", roleParam) &
                validateAndPut(redirect, name, "name", nameParam) &
                validateAndPut(redirect, surname, "surname", surnameParam) &
                validateAndPut(redirect, house, "house", houseParam) &
                validateAndPut(redirect, room, "room", roomParam) &
                validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPut(redirect, phone, "phone", phoneParam) &
                validateAndPut(redirect, email, "email", emailParam) &
                validateAndPut(redirect, street, "street", streetParam);

        if (result) {
            return User.newBuilder()
                    .username(username.get())
                    .password(password.get())
                    .role(role.get())
                    .name(name.get())
                    .surname(surname.get())
                    .house(house.get())
                    .room(room.get())
                    .porch(porch.get())
                    .floor(floor.get())
                    .phone(phone.get())
                    .email(email.get())
                    .creation(LocalDateTime.now())
                    .isBlocked(false)
                    .street(street.get())
                    .build();
        } else {
            req.getSession().setAttribute(REDIRECTED_INFO, redirect);
            return null;
        }
    }

    private boolean validateAndPut(Map<String, String> redirect, Optional<?> optional, String label, String param) {
        redirect.put(label, param);
        if (optional.isEmpty()) {
            redirect.put(label + POSTFIX, "true");
            return false;
        }
        log.debug("label = {}", label);
        log.debug("param = {}", param);
        return true;
    }
}
