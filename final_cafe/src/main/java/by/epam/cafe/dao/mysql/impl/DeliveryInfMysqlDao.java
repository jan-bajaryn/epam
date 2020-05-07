package by.epam.cafe.dao.mysql.impl;

import by.epam.cafe.dao.mysql.AbstractMysqlDao;
import by.epam.cafe.entity.impl.DeliveryInf;

import java.sql.*;
import java.time.LocalDateTime;

public class DeliveryInfMysqlDao extends AbstractMysqlDao<Integer, DeliveryInf> {

    // language=SQL
    private static final String findAllSql = "SELECT id, comments, delivery_time, email, floor, house, phone, porch, room, street FROM delivery_inf ORDER BY id;";
    // language=SQL
    private static final String findEntityByIdSql = "SELECT id, comments, delivery_time, email, floor, house, phone, porch, room, street FROM delivery_inf WHERE id = ?;";
    // language=SQL
    private static final String deleteByIdSql = "DELETE FROM delivery_inf WHERE id = ?;";
    // language=SQL
    private static final String createSql = "INSERT INTO delivery_inf (comments, delivery_time, email, floor, house, phone, porch, room, street) VALUES (?,?,?,?,?,?,?,?,?);";
    // language=SQL
    private static final String updateSql = "UPDATE delivery_inf SET  comments = ?, delivery_time = ?, email = ?, floor = ?, house = ?, phone = ?, porch = ?, room = ?, street = ? WHERE id = ?;";
    // language=SQL
    private static final String findAllByPart = "SELECT id, comments, delivery_time, email, floor, house, phone, porch, room, street FROM delivery_inf ORDER BY id LIMIT ? OFFSET ?;";


    public DeliveryInfMysqlDao() {
        super(findAllSql, findEntityByIdSql, deleteByIdSql, createSql, updateSql, findAllByPart);
    }


    @Override
    protected DeliveryInf findEntity(ResultSet resultSet) throws SQLException {
        return DeliveryInf.newBuilder()
                .id(resultSet.getInt("id"))
                .comments(resultSet.getString("comments"))
                .deliveryTime(toLocalDateTime(resultSet.getTimestamp("delivery_time")))
                .email(resultSet.getString("email"))
                .floor(getIntOrNull(resultSet, "floor"))
                .house(resultSet.getString("house"))
                .phone(resultSet.getString("phone"))
                .porch(getIntOrNull(resultSet, "porch"))
                .room(resultSet.getString("room"))
                .street(resultSet.getString("street"))
                .build();
    }


    private Integer getIntOrNull(ResultSet resultSet, String label) throws SQLException {
        Integer floor;
        int floorInt = resultSet.getInt(label);
        if (resultSet.wasNull()) {
            floor = null;
        } else {
            floor = floorInt;
        }
        return floor;
    }

    private LocalDateTime toLocalDateTime(Timestamp deliveryTime) {
        return deliveryTime == null ? null : deliveryTime.toLocalDateTime();
    }

    @Override
    protected void idParam(PreparedStatement statement, Integer integer) throws SQLException {
        statement.setInt(1, integer);
    }


    @Override
    protected void createParams(DeliveryInf entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getComments());
        statement.setTimestamp(2, Timestamp.valueOf(entity.getDeliveryTime()));
        statement.setString(3, entity.getEmail());
        setIntOrNull(statement, entity.getFloor(), 4);
        statement.setString(5, entity.getHouse());
        statement.setString(6, entity.getPhone());
        setIntOrNull(statement, entity.getPorch(), 7);
        statement.setString(8, entity.getRoom());
        statement.setString(9, entity.getStreet());

    }


    private void setIntOrNull(PreparedStatement statement, Integer integer, int label) throws SQLException {
        if (integer == null) {
            statement.setNull(label, Types.INTEGER);
        } else {
            statement.setInt(label, integer);
        }
    }


    @Override
    protected void updateParams(DeliveryInf entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getComments());
        statement.setTimestamp(2, Timestamp.valueOf(entity.getDeliveryTime()));
        statement.setString(3, entity.getEmail());
        setIntOrNull(statement, entity.getFloor(), 4);
        statement.setString(5, entity.getHouse());
        statement.setString(6, entity.getPhone());
        setIntOrNull(statement, entity.getPorch(), 7);
        statement.setString(8, entity.getRoom());
        statement.setString(9, entity.getStreet());
        statement.setInt(10, entity.getId());
    }


    @Override
    protected Integer getIdFromGeneratedKeys(ResultSet generatedKeys) throws SQLException {
        return generatedKeys.getInt(1);
    }

}
