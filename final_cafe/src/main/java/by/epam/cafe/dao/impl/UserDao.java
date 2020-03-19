package by.epam.cafe.dao.impl;

import by.epam.cafe.dao.AbstractDao;
import by.epam.cafe.entity.impl.User;

import java.sql.*;
import java.time.ZoneId;

public class UserDao extends AbstractDao<Long, User> {

    public static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    public static final String FIND_ALL_SQL = "SELECT " +
            "id, username, password, role, name, surname," +
            " birth_date, creation, address, phone FROM user;";


    public static final String FIND_ENTITY_BY_ID_SQL = "SELECT " +
            "id, username, password, role, name, surname," +
            " birth_date, creation, address, phone FROM user" +
            " WHERE id = ";

    public static final String DELETE_BY_ID_SQL = "DELETE FROM user WHERE id = ";

    public static final String CREATE_SQL = "INSERT INTO user " +
            "(username, password, role, name, surname, birth_date, creation, address, phone) " +
            "VALUES (?,?,?,?,?,?,?,?,?)";

    public static final String UPDATE_SQL = "UPDATE user " +
            "SET username = ?, password = ?, role = ?, name = ?, surname = ?," +
            " birth_date = ?, creation = ?, address = ?, phone = ? " +
            "WHERE id = ?;";


    public UserDao(Connection cn) {
        super(cn, FIND_ALL_SQL, FIND_ENTITY_BY_ID_SQL, DELETE_BY_ID_SQL, CREATE_SQL, UPDATE_SQL);
    }

    protected User findEntity(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getDate(7).toInstant().atZone(DEFAULT_ZONE).toLocalDate(),
                resultSet.getDate(8).toInstant().atZone(DEFAULT_ZONE).toLocalDateTime(),
                resultSet.getString(9),
                resultSet.getString(10)
        );
    }

    protected void createParams(User entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getUsername());
        statement.setString(2, entity.getPassword());
        statement.setInt(3, entity.getRole());
        statement.setString(4, entity.getName());
        statement.setString(5, entity.getSurname());
        statement.setDate(6, Date.valueOf(entity.getBirthDate()));
        statement.setTimestamp(7, Timestamp.valueOf(entity.getCreation()));
        statement.setString(8, entity.getAddress());
        statement.setString(9, entity.getPhone());
    }


    protected void updateParams(User entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getUsername());
        statement.setString(2, entity.getPassword());
        statement.setInt(3, entity.getRole());
        statement.setString(4, entity.getName());
        statement.setString(5, entity.getSurname());
        statement.setDate(6, Date.valueOf(entity.getBirthDate()));
        statement.setTimestamp(7, Timestamp.valueOf(entity.getCreation()));
        statement.setString(8, entity.getAddress());
        statement.setString(9, entity.getPhone());
        statement.setLong(10, entity.getId());
    }
}
