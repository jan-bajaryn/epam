package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.UserService;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserList extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(UserList.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> all = userService.findAll();
        log.info("execute: all = {}", all);
        request.setAttribute("users", all);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/user-list.jsp").forward(request, response);
    }
}
