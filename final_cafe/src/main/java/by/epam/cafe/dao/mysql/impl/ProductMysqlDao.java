package by.epam.cafe.dao.mysql.impl;

import by.epam.cafe.dao.mysql.AbstractMysqlDao;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductMysqlDao extends AbstractMysqlDao<Integer, Product> {

    private static final Logger log = LogManager.getLogger(ProductMysqlDao.class);


    // language=SQL
    private static final String FIND_ALL_SQL = "SELECT id, price, weight, product_group_id FROM product;";
    // language=SQL
    private static final String FIND_BY_ID_SQL = "SELECT id, price, weight, product_group_id FROM product WHERE id = ?;";
    // language=SQL
    private static final String DELETE_BY_ID = "DELETE FROM product WHERE id = ?";
    // language=SQL
    private static final String CREATE_SQL = "INSERT INTO product (price, weight, product_group_id) VALUES (?,?,?);";
    // language=SQL
    private static final String UPDATE_SQL = "UPDATE product SET  price = ?, weight = ?, product_group_id = ? WHERE id = ?";



    public ProductMysqlDao() {
        super(FIND_ALL_SQL, FIND_BY_ID_SQL, DELETE_BY_ID, CREATE_SQL, UPDATE_SQL);
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
        statement.setInt(3, entity.getProductGroup().getId());
    }

    protected void updateParams(Product entity, PreparedStatement statement) throws SQLException {
        statement.setInt(1, entity.getPrice());
        statement.setInt(2, entity.getWeight());
        statement.setInt(3, entity.getProductGroup().getId());
        statement.setInt(4, entity.getId());
    }
}
