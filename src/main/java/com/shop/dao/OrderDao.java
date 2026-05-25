package com.shop.dao;

import com.shop.entity.CartItem;
import com.shop.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Collection;

public class OrderDao {
    public int createOrder(int buyerId, String orderNo, BigDecimal total, Collection<CartItem> items) throws Exception {
        Connection connection = C3p0Utils.getDataSource().getConnection();
        try {
            connection.setAutoCommit(false);
            QueryRunner runner = new QueryRunner();
            Number orderId = runner.insert(connection,
                    "INSERT INTO orders(order_no, buyer_id, total_amount, status) VALUES(?,?,?,?)",
                    new ScalarHandler<>(), orderNo, buyerId, total, "CREATED");
            ProductDao productDao = new ProductDao();
            for (CartItem item : items) {
                productDao.decreaseStock(connection, item.getProduct().getId(), item.getQuantity());
                runner.update(connection,
                        "INSERT INTO order_item(order_id, product_id, product_name, price, quantity, subtotal) VALUES(?,?,?,?,?,?)",
                        orderId.intValue(), item.getProduct().getId(), item.getProduct().getName(),
                        item.getFinalPrice(), item.getQuantity(), item.getSubtotal());
            }
            connection.commit();
            return orderId.intValue();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }
}
