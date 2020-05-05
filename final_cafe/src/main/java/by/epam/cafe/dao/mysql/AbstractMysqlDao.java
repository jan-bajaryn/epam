package by.epam.cafe.dao.mysql;

import by.epam.cafe.dao.AbstractDao;
import by.epam.cafe.dao.pool.ConnectionPool;
import by.epam.cafe.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMysqlDao<ID, T extends Entity<ID>> implements AbstractDao<ID, T> {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final Logger log = LogManager.getLogger(AbstractMysqlDao.class);


    private String findAllSql;
    private String findEntityByIdSql;
    private String deleteByIdSql;
    private String createSql;
    private String updateSql;

    public AbstractMysqlDao(
            String findAllSql,
            String findEntityByIdSql,
            String deleteByIdSql,
            String createSql,
            String updateSql) {
        this.findAllSql = findAllSql;
        this.findEntityByIdSql = findEntityByIdSql;
        this.deleteByIdSql = deleteByIdSql;
        this.createSql = createSql;
        this.updateSql = updateSql;
    }

    @Override
    public List<T> findAll(Transaction transaction) {

        Connection cn = transaction.getConnection();


        List<T> entities = new ArrayList<>();

        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(findAllSql);
            while (resultSet.next()) {
                T entity = findEntity(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            log.info("e: ", e);
        }
        return entities;
    }

    protected abstract T findEntity(ResultSet resultSet) throws SQLException;


    /**
     * @param id Identifier of entity in {@code MySQL} database
     * @return instance of class {@link Entity}
     * @throws NullPointerException if the input is {@code null}
     *                              {@link NullPointerException}
     */
    @Override
    public T findEntityById(ID id, Transaction transaction) {

        Connection cn = transaction.getConnection();

        T entity = null;
        try (PreparedStatement statement =
                     cn.prepareStatement(findEntityByIdSql)) {
            idParam(statement, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = findEntity(resultSet);
            }
        } catch (SQLException e) {
            log.info("e: ", e);
        }
        return entity;

    }

    protected abstract void idParam(PreparedStatement statement, ID id) throws SQLException;

    @Override
    public boolean deleteById(ID id, Transaction transaction) {
        Connection cn = transaction.getConnection();

        try (PreparedStatement statement = cn.prepareStatement(deleteByIdSql)) {
            idParam(statement, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(T entity, Transaction transaction) {
        Connection cn = transaction.getConnection();

        try (PreparedStatement statement = cn.prepareStatement(deleteByIdSql)) {
            idParam(statement, entity.getId());
            return statement.execute();
        } catch (SQLException e) {
            log.info("e: ", e);
        }
        return false;
    }

    @Override
    public T create(T entity, Transaction transaction) {
        Connection cn = transaction.getConnection();

        try (PreparedStatement statement =
                     cn.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS)) {
            createParams(entity, statement);
//                return statement.execute();
//                return statement.executeUpdate() == 1;
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(getIdFromGeneratedKeys(generatedKeys));
                    return entity;
                }
            }

        } catch (SQLException e) {
            log.info("e: ", e);
        }
        return null;
    }

    protected abstract ID getIdFromGeneratedKeys(ResultSet generatedKeys) throws SQLException;

    protected abstract void createParams(T entity, PreparedStatement statement) throws SQLException;

    @Override
    public boolean update(T entity, Transaction transaction) {
        Connection cn = transaction.getConnection();
        try (PreparedStatement statement =
                     cn.prepareStatement(updateSql)) {

            updateParams(entity, statement);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            log.info("e: ", e);
        }
        return false;
    }

    protected abstract void updateParams(T entity, PreparedStatement statement) throws SQLException;

}
