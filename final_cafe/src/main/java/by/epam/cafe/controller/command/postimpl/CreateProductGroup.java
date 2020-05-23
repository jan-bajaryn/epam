package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.entity.struct.ValueHolder;
import by.epam.cafe.service.db.ProductGroupService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.helper.ImageWriterService;
import by.epam.cafe.service.parser.full.ProductGroupParser;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.cafe.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class CreateProductGroup extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(CreateProductGroup.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();
    private final ImageWriterService imageWriterService = serviceFactory.getImageWriterServiceImpl();
    private static final DiskFileItemFactory FILE_ITEM_FACTORY = new DiskFileItemFactory();


    private final ProductGroupParser productGroupParser = serviceFactory.getProductGroupParserImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        String referer = request.getHeader("referer");

        Map<String, String> redirect = new HashMap<>();
        ValueHolder<String> fileNameHolder = new ValueHolder<>();

        ProductGroup build = parseRequest(request, redirect, fileNameHolder);

        if (build != null) {

            try {
                if (productGroupService.create(build) != null) {
                    response.sendRedirect(request.getContextPath() + request.getServletPath() + "/admin/product-group-list?pagination=1");
                } else {
                    imageWriterService.deleteImageIfNeed(fileNameHolder.getValue());
                    request.setAttribute("unknown_error", "true");
                    response.sendRedirect(referer);
                }
            } catch (ServiceException e) {
                imageWriterService.deleteImageIfNeed(fileNameHolder.getValue());
                request.setAttribute("unknown_error", "true");
                response.sendRedirect(referer);
            }
        } else {
            imageWriterService.deleteImageIfNeed(fileNameHolder.getValue());
            request.getSession().setAttribute(REDIRECTED_INFO, redirect);
            response.sendRedirect(referer);
        }

//            if (withoutId && productGroupService.create(productGroup) != null) {
//                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/admin/product-group-list");
//            } else {
//                imageWriterService.deleteImageIfNeed(productGroup.getPhotoName());
//                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
//            }

    }

    public ProductGroup parseRequest(HttpServletRequest request, Map<String, String> redirect, ValueHolder<String> holderFileName) {
        try {
            boolean isRight = true;
            ProductGroup productGroup = new ProductGroup();

            ServletFileUpload fileUpload = new ServletFileUpload(FILE_ITEM_FACTORY);
            List<FileItem> parts = fileUpload.parseRequest(request);

            for (FileItem part : parts) {
                isRight = isRight && productGroupParser.fillFields(productGroup, part, redirect, holderFileName);
            }
            if (!isRight) {
                return null;
            }
            return productGroup;
        } catch (FileUploadException e) {
            log.error("e: ", e);
            return null;
        }
    }

}
