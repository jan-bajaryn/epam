package by.epam.cafe.dao.impl;

import by.epam.cafe.dao.AbstractDao;
import by.epam.cafe.entity.impl.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.cafe.dao.impl.UserDao.DEFAULT_ZONE;

public class OrderDao implements AbstractDao<Integer, Order> {

    private static final String FIND_BY_ID_SQL = "SELECT id, creation, price, status, payment_type FROM `order` WHERE id = ";
    private static final String DELETE_BY_ID = "DELETE FROM `order` WHERE id = ";
    private static final String CREATE_SQL = "INSERT INTO `order` (creation, price, status, payment_type) VALUES (?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE `order` SET creation = ?, price = ?, status = ?, payment_type = ? WHERE id = ?;";

    private Connection cn;

    public OrderDao(Connection cn) {
        this.cn = cn;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, creation, price, status, payment_type FROM `order`;");
            while (resultSet.next()) {
                Order order = findOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order findOrder(ResultSet resultSet) throws SQLException {
        return new Order(
                resultSet.getLong(1),
                resultSet.getDate(2).toInstant().atZone(DEFAULT_ZONE).toLocalDateTime(),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getInt(5)
        );
    }

    @Override
    public Order findEntityById(Integer id) {
        Order order = null;
        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_BY_ID_SQL + id.toString() + ";");
            if (resultSet.next()) {
                order = findOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean delete(Integer id) {
        try (Statement statement = cn.createStatement()) {
            return statement.execute(DELETE_BY_ID
                    + id.toString() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Order entity) {
        try (Statement statement = cn.createStatement()) {
            return statement.execute(DELETE_BY_ID
                    + entity.getId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(Order entity) {
        try (PreparedStatement statement =
                     cn.prepareStatement(CREATE_SQL)) {

            createParams(entity, statement);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createParams(Order entity, PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(entity.getCreation()));
        statement.setInt(2, entity.getPrice());
        statement.setInt(2, entity.getStatus());
        statement.setInt(3, entity.getPaymentType());
    }

    @Override
    public Order update(Order entity) {
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

    private void updateParams(Order entity, PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(entity.getCreation()));
        statement.setInt(2, entity.getPrice());
        statement.setInt(3, entity.getStatus());
        statement.setInt(4, entity.getPaymentType());
    }
}
