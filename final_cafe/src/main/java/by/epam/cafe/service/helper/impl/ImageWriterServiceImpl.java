package by.epam.cafe.service.helper.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.ImageWriterDao;
import by.epam.cafe.service.helper.ImageWriterService;
import org.apache.commons.fileupload.FileItem;

import java.io.File;

public class ImageWriterServiceImpl implements ImageWriterService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();

    final ImageWriterDao imageWriterDao = dAOFactory.getImageWriterDao();

    /**
     * @param part FileItem from multipart form data
     * @return created file
     * @throws Exception if service can't create the file
     */
    @Override
    public File downloadFile(FileItem part) throws Exception {
        return imageWriterDao.downloadFile(part);
    }

    /**
     * Deletes file by path if the file exists
     *
     * @param photoName path to file to delete
     */
    @Override
    public void deleteImageIfNeed(String photoName) {
        if (photoName != null) {
            imageWriterDao.deleteIfNeed(photoName);
        }
    }
}
