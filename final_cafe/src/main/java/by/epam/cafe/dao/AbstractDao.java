package by.epam.cafe.dao;

import by.epam.cafe.entity.Entity;
import by.epam.cafe.entity.impl.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<K, T extends Entity<K>> {

    protected Connection cn;

    private String findAllSql;
    private String findEntityByIdSql;
    private String deleteByIdSql;
    private String createSql;
    private String updateSql;

    public AbstractDao(Connection cn,
                       String findAllSql,
                       String findEntityByIdSql,
                       String deleteByIdSql,
                       String createSql,
                       String updateSql) {
        this.cn = cn;
        this.findAllSql = findAllSql;
        this.findEntityByIdSql = findEntityByIdSql;
        this.deleteByIdSql = deleteByIdSql;
        this.createSql = createSql;
        this.updateSql = updateSql;
    }

    public List<T> findAll() {
        List<T> entities = new ArrayList<>();

        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(findAllSql);
            while (resultSet.next()) {
                T entity = findEntity(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    protected abstract T findEntity(ResultSet resultSet) throws SQLException;


    public T findEntityById(K id) {
        T entity = null;
        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(findEntityByIdSql + id.toString() + ";");
            if (resultSet.next()) {
                entity = findEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public boolean delete(K id) {
        try (Statement statement = cn.createStatement()) {
            return statement.execute(deleteByIdSql
                    + id.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(T entity) {
        try (Statement statement = cn.createStatement()) {
            return statement.execute(deleteByIdSql
                    + entity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean create(T entity) {
        try (PreparedStatement statement =
                     cn.prepareStatement(createSql)) {

            createParams(entity, statement);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected abstract void createParams(T entity, PreparedStatement statement) throws SQLException;

    // TODO ask question about what I must return in that method
    public T update(T entity) {
        try (PreparedStatement statement =
                     cn.prepareStatement(updateSql)) {

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

    protected abstract void updateParams(T entity, PreparedStatement statement) throws SQLException;

}
