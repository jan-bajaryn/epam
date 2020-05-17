package by.epam.cafe.dao.mysql.impl;


import by.epam.cafe.dao.mysql.AbstractMysqlDao;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class OrderMysqlDao extends AbstractMysqlDao<Integer, Order> {

    private static final Logger log = LogManager.getLogger(OrderMysqlDao.class);
    private static final String FIND_CURRENT = "SELECT id, client_name, creation, payment_type, price, status, delivery_inf_id, user_id FROM `order` WHERE `status` = ? AND  user_id = ?;";

    private final DaoHelper daoHelper = DaoHelper.getInstance();

    /*language=SQL*/
    private static final String FIND_ALL_SQL = "SELECT id, client_name, creation, payment_type, price, status, delivery_inf_id, user_id FROM `order` ORDER BY id;";
    /*language=SQL*/
    private static final String findAllByPart = "SELECT id, client_name, creation, payment_type, price, status, delivery_inf_id, user_id FROM `order` ORDER BY id LIMIT ? OFFSET ?;";

    /*language=SQL*/
    private static final String FIND_BY_ID_SQL = "SELECT id, client_name, creation, payment_type, price, status, delivery_inf_id, user_id FROM `order` WHERE id = ?;";
    /*language=SQL*/
    private static final String DELETE_BY_ID = "DELETE FROM `order` WHERE id = ?";
    /*language=SQL*/
    private static final String CREATE_SQL = "INSERT INTO `order` (client_name, creation, payment_type, price, status, delivery_inf_id, user_id)" +
            " VALUES (?,?,?,?,?,?,?);";
    /*language=SQL*/
    private static final String UPDATE_SQL = "UPDATE `order` SET  client_name = ?, creation = ?, payment_type = ?, price = ?, status = ?, delivery_inf_id = ?, user_id = ?" +
            " WHERE id = ?;";

    // language=SQL
    private static final String countSql = "SELECT count(id) FROM `order`;";


    /*language=SQL*/
    private static final String FIND_PRODUCTS_BY_ORDER_SQL = "SELECT product_id from `order` INNER JOIN order_product ON `order`.id = order_product.order_id WHERE order_id = ?;";
    private static final String insertProducts = "INSERT INTO order_product(order_id, product_id, count) VALUES (?,?,?);";
    private static final String ADD_FIRST_PRODUCT = "INSERT INTO order_product(order_id, product_id, count) VALUES (?,?,1);";
    private static final String INCREASE_COUNT_PROD = "UPDATE order_product SET count = count+1 WHERE product_id = ? and  order_id = ?;";
    private static final String REMOVE_PRODUCT = "DELETE FROM order_product WHERE order_id=? AND product_id = ?;";
    private static final String MINUS_PRODUCT = "UPDATE order_product SET count = count -1 WHERE product_id=? and order_id = ?;";

    public OrderMysqlDao() {
        super(FIND_ALL_SQL, FIND_BY_ID_SQL, DELETE_BY_ID, CREATE_SQL, UPDATE_SQL, findAllByPart, countSql);
    }


    public List<Integer> findAllProductsIdsByOrderId(Integer id, Transaction transaction) {
        Connection cn = transaction.getConnection();

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
    }


    @Override
    protected Order findEntity(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        Timestamp timestamp = resultSet.getTimestamp("creation");
        int userId = resultSet.getInt("user_id");
        int delInfId = resultSet.getInt("delivery_inf_id");
        return Order.newBuilder()
                .id(id)
                .clientName(resultSet.getString("client_name"))
                .creation(timestamp == null ? null : timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .paymentType(PaymentType.values()[resultSet.getInt("payment_type")])
                .price(resultSet.getInt("price"))
                .status(OrderStatus.values()[resultSet.getInt("status")])
                .deliveryInf(delInfId != 0 ? DeliveryInf.newBuilder().id(delInfId).build() : null)
                .user(userId != 0 ? User.newBuilder().id(userId).build() : null)
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
        statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        daoHelper.setOrdinalOrNull(entity.getPaymentType(), statement, 3);
//        statement.setInt(3, );
//        statement.setInt(4, entity.getPrice());
        daoHelper.setIntOrNull(statement, entity.getPrice(), 4);
        daoHelper.setOrdinalOrNull(entity.getStatus(), statement, 5);

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

        log.debug("entity.getUser() = {}", entity.getUser());
        if (entity.getUser() != null) {
            statement.setInt(7, entity.getUser().getId());
        } else {
            statement.setNull(7, Types.INTEGER);
        }

        statement.setInt(8, entity.getId());
//        statement.setInt(7, entity.getId());
    }


    @Override
    protected Integer getIdFromGeneratedKeys(ResultSet generatedKeys) throws SQLException {
        return generatedKeys.getInt(1);
    }

    public boolean addProductsOnCreate(Map<Product, Integer> products, Order order, Transaction transaction) {
        Connection cn = transaction.getConnection();

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
    }

    private void insertProdsParams(Map.Entry<Product, Integer> entry, Order order,
                                   PreparedStatement statement) throws SQLException {
        statement.setInt(1, order.getId());
        statement.setInt(2, entry.getKey().getId());
        statement.setInt(3, entry.getValue());
    }

    public boolean plusProductFirst(Integer orderId, Integer prodId, Transaction transaction) {
        Connection cn = transaction.getConnection();

        try (PreparedStatement statement =
                     cn.prepareStatement(ADD_FIRST_PRODUCT)) {

            statement.setInt(1, orderId);
            statement.setInt(2, prodId);

            int affectedRows = statement.executeUpdate();
            return affectedRows == 1;

        } catch (SQLException e) {
            log.info("e: ", e);
            return false;
        }
    }

    public boolean plusExistingProduct(Integer orderId, Integer prodId, Transaction transaction) {
        Connection cn = transaction.getConnection();

        try (PreparedStatement statement =
                     cn.prepareStatement(INCREASE_COUNT_PROD)) {


            statement.setInt(1, prodId);
            statement.setInt(2, orderId);

            int affectedRows = statement.executeUpdate();
            return affectedRows == 1;

        } catch (SQLException e) {
            log.info("e: ", e);
            return false;
        }
    }

    public boolean removeProduct(Integer orderId, Integer prodId, Transaction transaction) {
        Connection cn = transaction.getConnection();

        try (PreparedStatement statement =
                     cn.prepareStatement(REMOVE_PRODUCT)) {


            statement.setInt(1, orderId);
            statement.setInt(2, prodId);

            int affectedRows = statement.executeUpdate();
            return affectedRows == 1;

        } catch (SQLException e) {
            log.info("e: ", e);
            return false;
        }
    }

    public boolean minusProduct(Integer orderId, Integer prodId, Transaction transaction) {
        Connection cn = transaction.getConnection();

        try (PreparedStatement statement =
                     cn.prepareStatement(MINUS_PRODUCT)) {


            statement.setInt(1, prodId);
            statement.setInt(2, orderId);

            int affectedRows = statement.executeUpdate();
            return affectedRows == 1;

        } catch (SQLException e) {
            log.info("e: ", e);
            return false;
        }
    }

    public Order findCurrentByUserId(Integer userId, Transaction transaction) {

        log.debug("findCurrentByUserId: userId = {}", userId);

        Connection cn = transaction.getConnection();


        try (PreparedStatement statement =
                     cn.prepareStatement(FIND_CURRENT)) {


            statement.setInt(1, OrderStatus.WAITING.ordinal());
            statement.setInt(2, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return findEntity(resultSet);
            }
            return null;
        } catch (SQLException e) {
            log.info("e: ", e);
            return null;
        }
    }
}
