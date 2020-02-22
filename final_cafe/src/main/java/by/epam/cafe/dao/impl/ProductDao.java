package by.epam.cafe.dao.impl;

import by.epam.cafe.dao.AbstractDao;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDao implements AbstractDao<Long, Product> {

    private static final String FIND_ALL_SQL = "SELECT id,name,description,photo_name,price,product_type_id,product_size FROM product;";
    private static final String FIND_BY_ID_SQL = "SELECT id,name,description,photo_name,price,product_type_id,product_size FROM product WHERE id = ";
    private static final String DELETE_BY_ID = "DELETE FROM product WHERE id = ";
    private static final String CREATE_SQL = "INSERT INTO product (name, description, photo_name, price, product_type_id, product_size) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE_SQL = "UPDATE product SET name = ?, description = ?," +
            " photo_name = ?, price = ?," +
            " product_type_id = ?, product_size = ?" +
            " WHERE id = ?;";
    private Connection cn;

    public ProductDao(Connection cn) {
        this.cn = cn;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL);
            while (resultSet.next()) {
                Product product = findProduct(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private Product findProduct(ResultSet resultSet) throws SQLException {
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

    @Override
    public Product findEntityById(Long id) {
        Product product = null;
        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_BY_ID_SQL + id.toString() + ";");
            if (resultSet.next()) {
                product = findProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean delete(Long id) {
        try (Statement statement = cn.createStatement()) {
            return statement.execute(DELETE_BY_ID
                    + id.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Product entity) {
        try (Statement statement = cn.createStatement()) {
            return statement.execute(DELETE_BY_ID
                    + entity.getId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(Product entity) {
        try (PreparedStatement statement =
                     cn.prepareStatement(CREATE_SQL)) {

            createParams(entity, statement);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createParams(Product entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setString(3, entity.getPhotoName());
        statement.setInt(4, entity.getPrice());
        statement.setInt(5, entity.getProductType().getId());
        statement.setInt(6, entity.getProductSize());
    }

    @Override
    public Product update(Product entity) {
        try (PreparedStatement statement =
                     cn.prepareStatement(UPDATE_SQL)) {

            updateParams(entity, statement);
            if (statement.execute()) {
                return entity;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateParams(Product entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setString(3, entity.getPhotoName());
        statement.setInt(4, entity.getPrice());
        statement.setInt(5, entity.getProductType().getId());
        statement.setInt(6, entity.getProductSize());
        statement.setLong(7, entity.getId());
    }
}
