package by.epam.cafe.service.db;

import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;


/**
 * Service interface to working with {@link ProductGroup}
 * Gives abilities for CRUD operations with {@link ProductGroup}
 */
public interface ProductGroupService {

    /**
     * @return List of all {@link ProductGroup} in base
     * @throws ServiceException if service can't connect to the database
     */
    List<ProductGroup> findAll() throws ServiceException;

    /**
     * @param part number of part of all entities {@link ProductGroup} from the database,
     *             where maximum number of entities in one part is
     *             {@value by.epam.cafe.config.Configuration#MAX_PAGINATION_ELEMENTS}
     * @return List of {@link ProductGroup} from the database related to part from input
     * or empty list if there no so part in database
     * @throws ServiceException if service can't connect to the database
     */
    List<ProductGroup> findAllByPart(int part) throws ServiceException;

    /**
     * @param id identifier of {@link ProductGroup}
     * @return entity from database identified by id, or {@code null} if
     * there no entity with so id
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    ProductGroup findEntityById(Integer id) throws ServiceException;

    /**
     * @param entity {@link ProductGroup} dedicated to create
     * @return created entity with new id, or {@code null} if entity can't be created
     * @throws ServiceException if service can't connect to the database
     */
    ProductGroup create(ProductGroup entity) throws ServiceException;


    /**
     * @param entity {@link ProductGroup} dedicated to update identified by id
     *               {@link by.epam.cafe.entity.db.Entity}
     * @return true if entity successfully updated otherwise returns false
     * @throws ServiceException if service can't connect to the database
     */
    boolean update(ProductGroup entity) throws ServiceException;

    /**
     * @return List of {@link ProductGroup} from the database
     * where method {@link ProductGroup#isDisabled()} returns false
     * @throws ServiceException if service can't connect to the database
     */
    List<ProductGroup> findAllByProductTypeNotDisabled(ProductType type) throws ServiceException;


    /**
     * @param productGroup {@link ProductGroup} without what should be return list
     * @return List of all {@link ProductGroup} from the database
     * except productGroup from input
     * @throws ServiceException if service can't connect to the database
     */
    List<ProductGroup> findAllExcept(ProductGroup productGroup) throws ServiceException;

    /**
     * Make {@link ProductGroup} disabled {@link ProductGroup#setDisabled(boolean)} true
     * and commit to the database
     *
     * @param id identifier of {@link ProductGroup}
     * @throws ServiceException                                    if service can't connect to the database
     * @throws by.epam.cafe.service.exception.NullServiceException if id is {@code null}
     * @see by.epam.cafe.entity.db.Entity
     */
    void disableById(Integer id) throws ServiceException;

    /**
     * Make {@link ProductGroup} disabled {@link ProductGroup#setDisabled(boolean)} false
     * and than commit to the database
     *
     * @param id identifier of {@link ProductGroup}
     * @throws ServiceException                                    if service can't connect to the database
     * @throws by.epam.cafe.service.exception.NullServiceException if id is {@code null}
     * @see by.epam.cafe.entity.db.Entity
     */
    void enableById(Integer id) throws ServiceException;

    /**
     * @return count of {@link ProductGroup} in the database
     * @throws ServiceException if service can't connect to the database
     */
    int count() throws ServiceException;

}
