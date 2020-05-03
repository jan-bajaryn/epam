package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.entity.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUser extends by.epam.cafe.controller.command.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("roles", Role.values());
        request.getRequestDispatcher("/WEB-INF/jsp/admin/create-user.jsp").forward(request, response);
    }
}
