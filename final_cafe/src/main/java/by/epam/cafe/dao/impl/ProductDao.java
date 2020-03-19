package by.epam.cafe.dao.impl;

import by.epam.cafe.dao.AbstractDao;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductDao extends AbstractDao<Long, Product> {

    private static final String FIND_ALL_SQL = "SELECT id,name,description,photo_name,price,product_type_id,product_size FROM product;";
    private static final String FIND_BY_ID_SQL = "SELECT id,name,description,photo_name,price,product_type_id,product_size FROM product WHERE id = ";
    private static final String DELETE_BY_ID = "DELETE FROM product WHERE id = ";
    private static final String CREATE_SQL = "INSERT INTO product (name, description, photo_name, price, product_type_id, product_size) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE_SQL = "UPDATE product SET name = ?, description = ?," +
            " photo_name = ?, price = ?," +
            " product_type_id = ?, product_size = ?" +
            " WHERE id = ?;";

    public ProductDao(Connection cn) {
        super(cn, FIND_ALL_SQL, FIND_BY_ID_SQL, DELETE_BY_ID, CREATE_SQL, UPDATE_SQL);
    }

    protected Product findEntity(ResultSet resultSet) throws SQLException {
        ProductType productType = new ProductType();
        productType.setId(resultSet.getInt(resultSet.getInt(6)));
        return new Product(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getInt(5),
                productType,
                resultSet.getInt(7)
        );
    }


    protected void createParams(Product entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setString(3, entity.getPhotoName());
        statement.setInt(4, entity.getPrice());
        statement.setInt(5, entity.getProductType().getId());
        statement.setInt(6, entity.getProductSize());
    }

    protected void updateParams(Product entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setString(3, entity.getPhotoName());
        statement.setInt(4, entity.getPrice());
        statement.setInt(5, entity.getProductType().getId());
        statement.setInt(6, entity.getProductSize());
        statement.setLong(7, entity.getId());
    }
}
