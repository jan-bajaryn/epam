package by.epam.cafe.controller.command;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.entity.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

public class CommandDecorator extends Command {
    private final Command command;
    private final EnumSet<Role> roles;

    public CommandDecorator(Command command, EnumSet<Role> roles) {
        this.command = command;
        this.roles = roles;
    }


    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        Role role = ((Role) request.getAttribute("role"));
        if (roles.contains(role)) {
            return command.execute(request, response);
        } else {
            throw new PermissionDeniedException();
        }
    }
}
