package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Forward;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.service.pagination.PaginationService;
import by.epam.cafe.service.db.ProductGroupService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.pagination.PaginationCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.cafe.config.Configuration.MAX_PAGINATION_ELEMENTS;

public class ProductGroupList extends by.epam.cafe.controller.command.Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();

    private final PaginationCalculator paginationCalculator = serviceFactory.getPaginationCalculator();

    private final PaginationService paginationService = serviceFactory.getPaginationService();


    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            int part = paginationCalculator.calculatePartParam(request.getParameter("pagination"));


            List<ProductGroup> all = productGroupService.findAllByPart(part);
            request.setAttribute("groups", all);
            request.setAttribute("paginationMap", paginationService.calculate(productGroupService.count(), part, MAX_PAGINATION_ELEMENTS));
            return new Forward("/admin/product-group-list.jsp");
        } catch (ServiceException e) {
            return new SendError(500);
        }

    }
}
