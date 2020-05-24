package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.exception.IllegalPathParamException;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.helper.PathVarCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserBlock extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(UserBlock.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            log.debug("UserBlockCommand begin");

            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            log.info("id = {}", id);

            userService.blockById(id);

            log.debug("block executed");
            return new Redirect("/admin/user-list?pagination=1");

        } catch (ServiceException e) {
            log.error("e: ", e);
            return new SendError(500);
        }
    }
}
