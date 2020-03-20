package by.epam.cafe.dao;

import by.epam.cafe.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<K, T extends Entity<K>> {

    private static final Logger log = LogManager.getLogger(AbstractDao.class);


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
            log.info("e.getMessage() = {}", e.getMessage());
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
            log.info("e.getMessage() = {}", e.getMessage());
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
            log.info("e.getMessage() = {}", e.getMessage());
        }
        return false;
    }

    public boolean create(T entity) {
        try (PreparedStatement statement =
                     cn.prepareStatement(createSql)) {

            createParams(entity, statement);
            return statement.execute();
        } catch (SQLException e) {
            log.info("e.getMessage() = {}", e.getMessage());
        }
        return false;
    }

    protected abstract void createParams(T entity, PreparedStatement statement) throws SQLException;

    public boolean update(T entity) {
        try (PreparedStatement statement =
                     cn.prepareStatement(updateSql)) {

            updateParams(entity, statement);
            return statement.execute();
        } catch (SQLException e) {
            log.info("e.getMessage() = {}", e.getMessage());
        }
        return false;
    }

    protected abstract void updateParams(T entity, PreparedStatement statement) throws SQLException;

}
