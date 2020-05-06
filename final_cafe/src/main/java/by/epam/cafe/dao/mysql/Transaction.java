package by.epam.cafe.dao.mysql;

import by.epam.cafe.dao.exception.DaoException;
import by.epam.cafe.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction implements AutoCloseable {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;

    public Transaction() {
        this.connection = connectionPool.takeConnection();
    }

    public void beginTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void rollBack() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.setAutoCommit(true);
            connectionPool.release(connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
