package by.epam.cafe.service.db;

import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;


/**
 * Service interface to working with {@link Product}
 * Gives abilities for CRUD operations with {@link Product}
 */
public interface ProductService {

    /**
     * @return List of all {@link Product} in base
     * @throws ServiceException if service can't connect to the database
     */
    List<Product> findAll() throws ServiceException;

    /**
     * @param part number of part of all entities {@link Product} from the database,
     *             where maximum number of entities in one part is
     *             {@value by.epam.cafe.config.Configuration#MAX_PAGINATION_ELEMENTS}
     * @return List of {@link Product} from the database related to part from input
     * or empty list if there no so part in database
     * @throws ServiceException if service can't connect to the database
     */
    List<Product> findAllByPart(int part) throws ServiceException;

    /**
     * @param id identifier of {@link Product}
     * @return entity from database identified by id, or {@code null} if
     * there no entity with so id
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    Product findEntityById(Integer id) throws ServiceException;

    /**
     * @param id identifier of {@link Product}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    boolean deleteById(Integer id) throws ServiceException;

    /**
     * @param entity what dedicated to delete {@link Product}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    boolean delete(Product entity) throws ServiceException;

    /**
     * @param entity {@link Product} dedicated to create
     * @return created entity with new id, or {@code null} if entity can't be created
     * @throws ServiceException if service can't connect to the database
     */
    Product create(Product entity) throws ServiceException;

    /**
     * @param entity {@link Product} dedicated to update identified by id
     *               {@link by.epam.cafe.entity.db.Entity}
     * @return true if entity successfully updated otherwise returns false
     * @throws ServiceException if service can't connect to the database
     */
    boolean update(Product entity) throws ServiceException;

    /**
     * @return List of {@link Product} from the database
     * where field {@link Product#getProductGroup()} by the method
     * {@link ProductGroup#isDisabled()} returns false
     * @throws ServiceException if service can't connect to the database
     */
    List<Product> findAllByProductGroupNotDisabled() throws ServiceException;

    /**
     * @return List of {@link Product} from the database
     * where method {@link Product#getProductGroup()} returns null
     * @throws ServiceException if service can't connect to the database
     */
    List<Product> findAllByProductGroupNull() throws ServiceException;

    /**
     * @return count of {@link Product} in the database
     * @throws ServiceException if service can't connect to the database
     */
    int count() throws ServiceException;

}
