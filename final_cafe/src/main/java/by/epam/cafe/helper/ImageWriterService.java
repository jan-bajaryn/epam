package by.epam.cafe.helper;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.ImageWriterDao;
import org.apache.commons.fileupload.FileItem;

import java.io.File;

public class ImageWriterService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();

    final ImageWriterDao imageWriterDao = dAOFactory.getImageWriterDao();

    public File downloadFile(FileItem part) throws Exception {
        return imageWriterDao.downloadFile(part);
    }

    public void deleteImageIfNeed(String photoName) {
        if (photoName != null) {
            imageWriterDao.deleteIfNeed(photoName);
        }
    }
}
