package by.epam.cafe.service.helper;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

/**
 * Dedicated to manage images in the project
 */
public interface ImageWriterService {
    /**
     * @param part FileItem from multipart form data
     * @return created file
     * @throws Exception if service can't create the file
     */
    File downloadFile(FileItem part) throws Exception;

    /**
     * Deletes file by path if the file exists
     *
     * @param photoName path to file to delete
     */
    void deleteImageIfNeed(String photoName);
}
