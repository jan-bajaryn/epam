package by.epam.cafe.dao.mysql.impl;

import by.epam.cafe.dao.mysql.AbstractMysqlDao;
import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.User;

import java.sql.*;
import java.time.ZoneId;

public class UserMysqlDao extends AbstractMysqlDao<Integer, User> {

    public static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();
    // language=SQL
    public static final String FIND_ALL_SQL = "SELECT id, creation, name, password, phone, role, surname, username, email, floor, house, porch, room, street, is_blocked FROM user;";


    // language=SQL
    public static final String FIND_ENTITY_BY_ID_SQL = "SELECT id, creation, name, password, phone, role, surname, username, email, floor, house, porch, room, street, is_blocked FROM user WHERE id = ?";

    // language=SQL
    public static final String DELETE_BY_ID_SQL = "DELETE FROM user WHERE id = ?";

    // language=SQL
    public static final String CREATE_SQL = "INSERT INTO user (creation, name, password, phone, role, surname, username, email, floor, house, porch, room, street, is_blocked) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    // language=SQL
    public static final String UPDATE_SQL = "UPDATE user SET  creation = ?, name = ?, password = ?, phone = ?, role = ?, surname = ?, username = ?, email = ?, floor = ?, house = ?, porch = ?, room = ?, street = ?, is_blocked = ? WHERE id = ?;";


    public UserMysqlDao(Connection cn) {
        super(FIND_ALL_SQL, FIND_ENTITY_BY_ID_SQL, DELETE_BY_ID_SQL, CREATE_SQL, UPDATE_SQL);
    }


    protected User findEntity(ResultSet resultSet) throws SQLException {
        return User.newBuilder()
                .id(resultSet.getInt(resultSet.getInt("id")))
                .creation(resultSet.getTimestamp("creation").toLocalDateTime())
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .phone(resultSet.getString("phone"))
                .role(Role.values()[resultSet.getInt("role")])
                .surname(resultSet.getString("surname"))
                .username(resultSet.getString("username"))
                .email(resultSet.getString("email"))
                .floor(getIntOrNull(resultSet, "floor"))
                .house(resultSet.getString("house"))
                .porch(getIntOrNull(resultSet, "porch"))
                .room(resultSet.getString("room"))
                .street(resultSet.getString("street"))
                .isBlocked(resultSet.getBoolean("is_blocked"))
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

    @Override
    protected void idParam(PreparedStatement statement, Integer integer) throws SQLException {
        statement.setInt(1, integer);
    }


    protected void createParams(User entity, PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(entity.getCreation()));
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getPassword());
        statement.setString(4, entity.getPhone());
        statement.setInt(5, entity.getRole().ordinal());
        statement.setString(6, entity.getSurname());
        statement.setString(7, entity.getUsername());
        statement.setString(8, entity.getEmail());
        setIntOrNull(statement, entity.getFloor(), 9);
        statement.setString(10, entity.getHouse());
        setIntOrNull(statement, entity.getPorch(), 11);
        statement.setString(12, entity.getRoom());
        statement.setString(13, entity.getStreet());
        statement.setBoolean(14, entity.getBlocked());
    }

    private void setIntOrNull(PreparedStatement statement, Integer integer, int label) throws SQLException {
        if (integer == null) {
            statement.setNull(label, Types.INTEGER);
        } else {
            statement.setInt(label, integer);
        }
    }

    protected void updateParams(User entity, PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(entity.getCreation()));
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getPassword());
        statement.setString(4, entity.getPhone());
        statement.setInt(5, entity.getRole().ordinal());
        statement.setString(6, entity.getSurname());
        statement.setString(7, entity.getUsername());
        statement.setString(8, entity.getEmail());
        setIntOrNull(statement, entity.getFloor(), 9);
        statement.setString(10, entity.getHouse());
        setIntOrNull(statement, entity.getPorch(), 11);
        statement.setString(12, entity.getRoom());
        statement.setString(13, entity.getStreet());
        statement.setBoolean(14, entity.getBlocked());
        statement.setInt(15, entity.getId());
    }
}
