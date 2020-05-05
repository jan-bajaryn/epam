package by.epam.cafe.dao.mysql.impl;

import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.dao.mysql.AbstractMysqlDao;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.ProductGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductGroupMysqlDao extends AbstractMysqlDao<Integer, ProductGroup> {

    private static final Logger log = LogManager.getLogger(ProductGroupMysqlDao.class);


    // language=SQL
    private static final String findAllSql = "SELECT id, description, name, photo_name, type, disabled FROM product_group;";
    // language=SQL
    private static final String findEntityByIdSql = "SELECT id, description, name, photo_name, type, disabled FROM product_group WHERE id = ?;";
    // language=SQL
    private static final String deleteByIdSql = "DELETE FROM product_group WHERE id = ?;";
    // language=SQL
    private static final String createSql = "INSERT INTO product_group (description, name, photo_name, type, disabled) VALUES (?,?,?,?,?);";
    // language=SQL
    private static final String updateSql = "UPDATE product_group SET  description = ?, name = ?, photo_name = ?, type = ?, disabled = ? WHERE id = ?;";
    // language=SQL
    private static final String findAllByProductGroupNotDisabled = "SELECT id, description, name, photo_name, type, disabled FROM product_group WHERE type = ? and disabled = ?;";

    // language=SQL
    public static final String findEmpty = "SELECT id, description, name, photo_name, type, disabled FROM product_group LEFT JOIN product ON product_group.id = product.product_group_id WHERE product_group_id IS NULL;";

    public ProductGroupMysqlDao() {
        super(findAllSql, findEntityByIdSql, deleteByIdSql, createSql, updateSql);
    }


    @Override
    protected ProductGroup findEntity(ResultSet resultSet) throws SQLException {
        return ProductGroup.newBuilder()
                .id(resultSet.getInt("id"))
                .description(resultSet.getString("description"))
                .name(resultSet.getString("name"))
                .photoName(resultSet.getString("photo_name"))
                .type(ProductType.values()[resultSet.getInt("type")])
                .disabled(resultSet.getBoolean("disabled"))
                .build();
    }

    @Override
    protected void idParam(PreparedStatement statement, Integer integer) throws SQLException {
        statement.setInt(1, integer);
    }


    @Override
    protected void createParams(ProductGroup entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getDescription());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getPhotoName());
        statement.setInt(4, entity.getType().ordinal());
        statement.setBoolean(5, entity.isDisabled());
    }

    @Override
    protected void updateParams(ProductGroup entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getDescription());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getPhotoName());
        statement.setInt(4, entity.getType().ordinal());
        statement.setBoolean(5, entity.isDisabled());
        statement.setInt(6, entity.getId());
    }

    public List<ProductGroup> findAllByProductTypeAndDisabled(ProductType type, boolean disabled, Transaction transaction) throws NullParamDaoException {
        if (type == null) {
            throw new NullParamDaoException("type is null");
        }
        log.debug("entered findAllByProductTypeAndDisabled");
        Connection cn = transaction.getConnection();

        log.info("findAllByProductTypeAndDisabled: cn = {}", cn);
        List<ProductGroup> productGroups = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement(findAllByProductGroupNotDisabled)) {

            statement.setInt(1, type.ordinal());
            statement.setBoolean(2, disabled);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductGroup entity = findEntity(resultSet);
                productGroups.add(entity);
            }
        } catch (SQLException e) {
            log.info("e: ", e);
        }

        return productGroups;
    }

    public List<ProductGroup> findAllEmpty(Transaction transaction) {

        Connection cn = transaction.getConnection();


        List<ProductGroup> entities = new ArrayList<>();

        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(findEmpty);
            while (resultSet.next()) {
                ProductGroup entity = findEntity(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            log.info("e: ", e);
        }
        return entities;
    }


    @Override
    protected Integer getIdFromGeneratedKeys(ResultSet generatedKeys) throws SQLException {
        return generatedKeys.getInt(1);
    }

}
