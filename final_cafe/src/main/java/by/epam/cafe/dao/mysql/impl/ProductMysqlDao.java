package by.epam.cafe.dao.mysql.impl;

import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.dao.mysql.AbstractMysqlDao;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductMysqlDao extends AbstractMysqlDao<Integer, Product> {

    private static final Logger log = LogManager.getLogger(ProductMysqlDao.class);


    // language=SQL
    private static final String FIND_ALL_SQL = "SELECT id, price, weight, product_group_id FROM product ORDER BY id;";
    // language=SQL
    private static final String findAllByPart = "SELECT id, price, weight, product_group_id FROM product ORDER BY id LIMIT ? OFFSET ?;";

    // language=SQL
    private static final String FIND_BY_ID_SQL = "SELECT id, price, weight, product_group_id FROM product WHERE id = ?;";
    // language=SQL
    private static final String DELETE_BY_ID = "DELETE FROM product WHERE id = ?";
    // language=SQL
    private static final String CREATE_SQL = "INSERT INTO product (price, weight, product_group_id) VALUES (?,?,?);";
    // language=SQL
    private static final String UPDATE_SQL = "UPDATE product SET  price = ?, weight = ?, product_group_id = ? WHERE id = ?";
    private static final String findProductByProductGroup = "SELECT id, price, weight, product_group_id FROM product WHERE product_group_id = ?;";
    // language=SQL
    private static final String findProductsByOrder = "SELECT id, price, weight, product_group_id, count FROM product INNER JOIN order_product ON product.id = order_product.product_id WHERE order_id = ?;";
    private static final String FIND_ALL_BY_PRODUCT_ID_NOT_DISABLED = "SELECT prod.id as id, prod.price as price, prod.weight as weight, prod.product_group_id as product_group_id FROM product as prod INNER JOIN product_group as prodgr ON prod.product_group_id = prodgr.id WHERE prodgr.disabled = FALSE ORDER BY prod.id;";
    // language=SQL
    private static final String countSql = "SELECT count(id) FROM product;";

    public ProductMysqlDao() {
        super(FIND_ALL_SQL, FIND_BY_ID_SQL, DELETE_BY_ID, CREATE_SQL, UPDATE_SQL, findAllByPart, countSql);
    }


    protected Product findEntity(ResultSet resultSet) throws SQLException {
        int productGroupId = resultSet.getInt("product_group_id");
        ProductGroup productGroup;
        if (productGroupId == 0) {
            productGroup = null;
        } else {
            productGroup = ProductGroup.newBuilder().id(productGroupId).build();
        }

        return Product.newBuilder()
                .id(resultSet.getInt("id"))
                .price(resultSet.getInt("price"))
                .weight(resultSet.getInt("weight"))
                .productGroup(productGroup)
                .build();
    }

    @Override
    protected void idParam(PreparedStatement statement, Integer integer) throws SQLException {
        statement.setInt(1, integer);
    }


    protected void createParams(Product entity, PreparedStatement statement) throws SQLException {
        statement.setInt(1, entity.getPrice());
        statement.setInt(2, entity.getWeight());
//        statement.setInt(3, entity.getProductGroup().getId());
        setProductGroup(3, entity.getProductGroup(), statement);
    }

    protected void updateParams(Product entity, PreparedStatement statement) throws SQLException {
        statement.setInt(1, entity.getPrice());
        statement.setInt(2, entity.getWeight());
//        statement.setInt(3, entity.getProductGroup().getId());
        setProductGroup(3, entity.getProductGroup(), statement);
        statement.setInt(4, entity.getId());
    }

    private void setProductGroup(int index, ProductGroup productGroup,
                                 PreparedStatement statement) throws SQLException {
        if (productGroup == null || productGroup.getId() == null) {
            statement.setNull(index, Types.INTEGER);
        } else {
            statement.setInt(index, productGroup.getId());
        }
    }

    public List<Product> findAllByProductGroupId(Integer id, Transaction transaction) throws NullParamDaoException {
        if (id == null) {
            throw new NullParamDaoException("id is null");
        }
        Connection cn = transaction.getConnection();

        List<Product> productGroups = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement(findProductByProductGroup)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product entity = findEntity(resultSet);
                productGroups.add(entity);
            }
        } catch (SQLException e) {
            log.info("e: ", e);
        }
        return productGroups;
    }


    @Override
    protected Integer getIdFromGeneratedKeys(ResultSet generatedKeys) throws SQLException {
        return generatedKeys.getInt(1);
    }

    public Map<Product, Integer> findAllByOrderId(Integer id, Transaction transaction) {

        Connection cn = transaction.getConnection();

        Map<Product, Integer> result = new HashMap<>();

        try (PreparedStatement statement = cn.prepareStatement(findProductsByOrder)) {
            idParam(statement, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product entity = findEntity(resultSet);
                Integer count = findCount(resultSet);
                result.put(entity, count);
            }
        } catch (SQLException e) {
            log.info("e: ", e);
        }
        return result;

    }

    private Integer findCount(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("count");
    }

    public List<Product> findAllByProductGroupNotDisabled(Transaction transaction) {
        Connection cn = transaction.getConnection();

        List<Product> entities = new ArrayList<>();

        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BY_PRODUCT_ID_NOT_DISABLED);
            while (resultSet.next()) {
                Product entity = findEntity(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            log.info("e: ", e);
        }
        return entities;
    }
}
