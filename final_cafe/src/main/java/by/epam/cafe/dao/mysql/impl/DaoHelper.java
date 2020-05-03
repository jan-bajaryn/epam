package by.epam.cafe.dao.mysql.impl;

import java.sql.*;

public class DaoHelper {
    public static final DaoHelper instance = new DaoHelper();

    public static DaoHelper getInstance() {
        return instance;
    }

    public void setIntOrNull(PreparedStatement statement, Integer integer, int label) throws SQLException {
        if (integer == null) {
            statement.setNull(label, Types.INTEGER);
        } else {
            statement.setInt(label, integer);
        }
    }

    public Integer getIntOrNull(ResultSet resultSet, String label) throws SQLException {
        Integer floor;
        int floorInt = resultSet.getInt(label);
        if (resultSet.wasNull()) {
            floor = null;
        } else {
            floor = floorInt;
        }
        return floor;
    }

    public void setOrdinalOrNull(Enum<?> enumeration, PreparedStatement statement, int index) throws SQLException {
        if (enumeration == null) {
            statement.setNull(index, Types.INTEGER);
        } else {
            statement.setInt(index, enumeration.ordinal());
        }
    }
}
