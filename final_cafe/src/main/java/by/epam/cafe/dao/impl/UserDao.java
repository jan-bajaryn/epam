package by.epam.cafe.dao.impl;

import by.epam.cafe.dao.AbstractDao;
import by.epam.cafe.entity.impl.User;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements AbstractDao<Long, User> {

    public static final String FIND_ALL_SQL = "SELECT " +
            "id, username, password, role, name, surname," +
            " birth_date, creation, address, phone FROM user;";

    public static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

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


    private Connection cn;

    public UserDao(Connection cn) {
        this.cn = cn;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL);
            while (resultSet.next()) {
                User user = findUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User findUser(ResultSet resultSet) throws SQLException {
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

    @Override
    public User findEntityById(Long id) {
        User user = null;
        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ENTITY_BY_ID_SQL + id.toString() + ";");
            if (resultSet.next()) {
                user = findUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean delete(Long id) {
        try (Statement statement = cn.createStatement()) {
            return statement.execute(DELETE_BY_ID_SQL
                    + id.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User entity) {
        try (Statement statement = cn.createStatement()) {
            return statement.execute(DELETE_BY_ID_SQL
                    + entity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(User entity) {
        try (PreparedStatement statement =
                     cn.prepareStatement(CREATE_SQL)) {

            createParams(entity, statement);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createParams(User entity, PreparedStatement statement) throws SQLException {
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


    // TODO ask question about what I must return in that method
    @Override
    public User update(User entity) {
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

    private void updateParams(User entity, PreparedStatement statement) throws SQLException {
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
