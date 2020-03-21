package by.epam.cafe.dao.impl;

import by.epam.cafe.dao.AbstractDao;

import java.sql.*;

import static by.epam.cafe.dao.impl.UserDao.DEFAULT_ZONE;


public class OrderDao extends AbstractDao<Long, Order> {

    /*language=SQL*/
    private static final String FIND_ALL_SQL = "SELECT id, creation, price, status, payment_type FROM `order`;";
    /*language=SQL*/
    private static final String FIND_BY_ID_SQL = "SELECT id, creation, price, status, payment_type FROM `order` WHERE id = ";
    /*language=SQL*/
    private static final String DELETE_BY_ID = "DELETE FROM `order` WHERE id = ";
    /*language=SQL*/
    private static final String CREATE_SQL = "INSERT INTO `order` (creation, price, status, payment_type) VALUES (?,?,?,?)";
    /*language=SQL*/
    private static final String UPDATE_SQL = "UPDATE `order` SET creation = ?, price = ?, status = ?, payment_type = ? WHERE id = ?;";

    public OrderDao(Connection cn) {
        super(cn, FIND_ALL_SQL, FIND_BY_ID_SQL, DELETE_BY_ID, CREATE_SQL, UPDATE_SQL);
    }

    @Override
    protected Order findEntity(ResultSet resultSet) throws SQLException {
        return new Order(
                resultSet.getLong(1),
                resultSet.getDate(2).toInstant().atZone(DEFAULT_ZONE).toLocalDateTime(),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getInt(5)
        );
    }


    protected void createParams(Order entity, PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(entity.getCreation()));
        statement.setInt(2, entity.getPrice());
        statement.setInt(3, entity.getStatus());
        statement.setInt(4, entity.getPaymentType());
    }


    protected void updateParams(Order entity, PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(entity.getCreation()));
        statement.setInt(2, entity.getPrice());
        statement.setInt(3, entity.getStatus());
        statement.setInt(4, entity.getPaymentType());
        statement.setLong(5, entity.getId());
    }
}
