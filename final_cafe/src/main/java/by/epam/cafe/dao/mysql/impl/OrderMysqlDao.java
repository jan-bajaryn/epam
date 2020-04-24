package by.epam.cafe.dao.mysql.impl;


import by.epam.cafe.dao.mysql.AbstractMysqlDao;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.entity.impl.DeliveryInf;
import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class OrderMysqlDao extends AbstractMysqlDao<Integer, Order> {

    private static final Logger log = LogManager.getLogger(OrderMysqlDao.class);


    public static final String ID_COL = "id";
    private static final String CLIENT_NAME_COL = "client_name";
    private static final String CREATION_COL = "creation";
    private static final String PAYMENT_TYPE_COL = "payment_type";
    private static final String PRICE_COL = "price";
    private static final String STATUS_ID_COL = "status";
    private static final String DELIVERY_INF_ID_COL = "delivery_inf_id";
    private static final String USER_ID_COL = "user_id";
    private static final String TABLE_NAME = "`order`";
    /*language=SQL*/
    private static final String FIND_ALL_SQL = "SELECT " + ID_COL + ", " +
            CLIENT_NAME_COL + ", " + CREATION_COL + ", " + PAYMENT_TYPE_COL + ", " +
            PRICE_COL + ", " + STATUS_ID_COL + ", " + DELIVERY_INF_ID_COL + ", " + USER_ID_COL +
            " FROM " + TABLE_NAME + ";";
    //    private static final String FIND_ALL_SQL = "SELECT id, creation, price, status, payment_type FROM `order`;";
    /*language=SQL*/
    private static final String FIND_BY_ID_SQL = "SELECT " + ID_COL + ", " +
            CLIENT_NAME_COL + ", " + CREATION_COL + ", " +
            PAYMENT_TYPE_COL + ", " + PRICE_COL + ", " +
            STATUS_ID_COL + ", " + DELIVERY_INF_ID_COL + ", " +
            USER_ID_COL + " FROM " + TABLE_NAME + " WHERE " + ID_COL + " = ?;";
    /*language=SQL*/
    private static final String DELETE_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COL + " = ?";
    /*language=SQL*/
    private static final String CREATE_SQL = "INSERT INTO " + TABLE_NAME +
            " (" + CLIENT_NAME_COL + ", " + CREATION_COL + ", " +
            PAYMENT_TYPE_COL + ", " + PRICE_COL + ", " + STATUS_ID_COL + ", " +
            DELIVERY_INF_ID_COL + ", " + USER_ID_COL + ")" +
            " VALUES (?,?,?,?,?,?,?);";
    /*language=SQL*/
    private static final String UPDATE_SQL = "UPDATE " + TABLE_NAME +
            " SET  " + CLIENT_NAME_COL + " = ?, " + CREATION_COL + " = ?, " +
            PAYMENT_TYPE_COL + " = ?, " + PRICE_COL + " = ?, " + STATUS_ID_COL + " = ?, " +
            DELIVERY_INF_ID_COL + " = ? " +
//             USER_ID_COL + " = ?" +
            " WHERE " + ID_COL + " = ?;";


    /*language=SQL*/
    private static final String FIND_PRODUCTS_BY_ORDER_SQL = "SELECT product_id from `order` INNER JOIN order_product ON `order`.id = order_product.order_id WHERE order_id = ?;";
    private static final String insertProducts = "INSERT INTO order_product(order_id, product_id, count) VALUES (?,?,?);";

    public OrderMysqlDao() {
        super(FIND_ALL_SQL, FIND_BY_ID_SQL, DELETE_BY_ID, CREATE_SQL, UPDATE_SQL);
    }


    public List<Integer> findAllProductsIdsByOrderId(Integer id) {
        Connection cn = getPool().takeConnection();
        try {
            List<Integer> ids = new ArrayList<>();

            try (PreparedStatement statement = cn.prepareStatement(FIND_PRODUCTS_BY_ORDER_SQL)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int product_id = resultSet.getInt("product_id");
                    ids.add(product_id);
                }
            } catch (SQLException e) {
                log.info("e: ", e);
            }
            return ids;
        } finally {
            getPool().release(cn);
        }
    }


    @Override
    protected Order findEntity(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt(ID_COL);
        Timestamp timestamp = resultSet.getTimestamp(CREATION_COL);
        return Order.newBuilder()
                .id(id)
                .clientName(resultSet.getString(CLIENT_NAME_COL))
                .creation(timestamp == null ? null : timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .paymentType(PaymentType.values()[resultSet.getInt(PAYMENT_TYPE_COL)])
                .price(resultSet.getInt(PRICE_COL))
                .status(OrderStatus.values()[resultSet.getInt(STATUS_ID_COL)])
                .deliveryInf(DeliveryInf.newBuilder().id(resultSet.getInt(DELIVERY_INF_ID_COL)).build())
                .user(User.newBuilder().id(resultSet.getInt(USER_ID_COL)).build())
//                .products(mapToEmptyProducts(findAllProductsIdsByOrderId(id)))
                .build();
    }

    private List<Product> mapToEmptyProducts(List<Integer> ids) {
        return ids.stream()
                .map(i -> Product.newBuilder().id(i).build())
                .collect(Collectors.toList());
    }

    @Override
    protected void idParam(PreparedStatement statement, Integer integer) throws SQLException {
        statement.setInt(1, integer);
    }


    protected void createParams(Order entity, PreparedStatement statement) throws SQLException {

        statement.setString(1, entity.getClientName());
        statement.setTimestamp(2, Timestamp.valueOf(entity.getCreation()));
        statement.setInt(3, entity.getPaymentType().ordinal());
        statement.setInt(4, entity.getPrice());
        statement.setInt(5, entity.getStatus().ordinal());

        if (entity.getDeliveryInf() != null) {
            statement.setInt(6, entity.getDeliveryInf().getId());
        } else {
            statement.setNull(6, Types.INTEGER);
        }

        if (entity.getUser() != null) {
            statement.setInt(7, entity.getUser().getId());
        } else {
            statement.setNull(7, Types.INTEGER);
        }
    }

    protected void updateParams(Order entity, PreparedStatement statement) throws SQLException {

        statement.setString(1, entity.getClientName());
        statement.setTimestamp(2, Timestamp.valueOf(entity.getCreation()));
        statement.setInt(3, entity.getPaymentType().ordinal());
        statement.setInt(4, entity.getPrice());
        statement.setInt(5, entity.getStatus().ordinal());

        if (entity.getDeliveryInf() != null) {
            statement.setInt(6, entity.getDeliveryInf().getId());
        } else {
            statement.setNull(6, Types.INTEGER);
        }
//        if (entity.getUser() != null) {
//            statement.setInt(7, entity.getUser().getId());
//        } else {
//            statement.setNull(7, Types.INTEGER);
//        }

//        statement.setInt(8, entity.getId());
        statement.setInt(7, entity.getId());
    }


    @Override
    protected Integer getIdFromGeneratedKeys(ResultSet generatedKeys) throws SQLException {
        return generatedKeys.getInt(1);
    }

    public boolean addProductsOnCreate(Map<Product, Integer> products, Order order) {
        Connection cn = getPool().takeConnection();

        try {
            for (Map.Entry<Product, Integer> productIntegerEntry : products.entrySet()) {
                try (PreparedStatement statement =
                             cn.prepareStatement(insertProducts)) {
                    insertProdsParams(productIntegerEntry, order, statement);
                    int affectedRows = statement.executeUpdate();

                    if (affectedRows != 1) {
                        return false;
                    }

                } catch (SQLException e) {
                    log.info("e: ", e);
                    return false;
                }
            }
            return true;
        } finally {
            getPool().release(cn);
        }
    }

    private void insertProdsParams(Map.Entry<Product, Integer> entry, Order order,
                                   PreparedStatement statement) throws SQLException {
        statement.setInt(1, order.getId());
        statement.setInt(2, entry.getKey().getId());
        statement.setInt(3, entry.getValue());
    }
}
