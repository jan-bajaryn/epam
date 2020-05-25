package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.controller.dto.UserDTO;
import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Forward;
import by.epam.cafe.entity.db.impl.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Cabinet extends by.epam.cafe.controller.command.Command {
    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", new UserDTO(user));

        return new Forward("/cabinet.jsp");
    }
}
