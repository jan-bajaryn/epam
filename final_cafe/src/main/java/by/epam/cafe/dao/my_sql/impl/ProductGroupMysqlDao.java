package by.epam.cafe.dao.my_sql.impl;

import by.epam.cafe.dao.my_sql.AbstractMysqlDao;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.ProductGroup;

import java.sql.*;

public class ProductGroupMysqlDao extends AbstractMysqlDao<Integer, ProductGroup> {
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
}
