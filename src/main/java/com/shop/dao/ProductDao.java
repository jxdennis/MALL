package com.shop.dao;

import com.shop.entity.Product;
import com.shop.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.util.List;

public class ProductDao {
    private final QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());

    private static final String PRODUCT_SELECT = """
            SELECT p.id, p.seller_id AS sellerId, u.username AS sellerName,
                   p.name, p.description, CAST(p.original_price AS DECIMAL(10,2)) AS originalPrice, p.stock,
                   p.image_path AS imagePath, p.discount_strategy_id AS discountStrategyId,
                   ds.strategy_name AS strategyName, ds.strategy_class AS strategyClass,
                   CAST(ds.discount_value AS DECIMAL(10,2)) AS discountValue
            FROM product p
            LEFT JOIN user u ON p.seller_id = u.id
            LEFT JOIN discount_strategy ds ON p.discount_strategy_id = ds.id
            """;

    public List<Product> findAll() throws Exception {
        return runner.query(PRODUCT_SELECT + " ORDER BY p.id DESC", new BeanListHandler<>(Product.class));
    }

    public List<Product> findBySellerId(int sellerId) throws Exception {
        return runner.query(PRODUCT_SELECT + " WHERE p.seller_id = ? ORDER BY p.id DESC",
                new BeanListHandler<>(Product.class), sellerId);
    }

    public Product findById(int id) throws Exception {
        return runner.query(PRODUCT_SELECT + " WHERE p.id = ?", new BeanHandler<>(Product.class), id);
    }

    public void add(Product product) throws Exception {
        String sql = "INSERT INTO product(seller_id, name, description, original_price, stock, image_path, discount_strategy_id) VALUES(?,?,?,?,?,?,?)";
        runner.update(sql, product.getSellerId(), product.getName(), product.getDescription(),
                product.getOriginalPrice(), product.getStock(), product.getImagePath(), product.getDiscountStrategyId());
    }

    public void update(Product product) throws Exception {
        String sql = "UPDATE product SET name=?, description=?, original_price=?, stock=?, discount_strategy_id=? WHERE id=? AND seller_id=?";
        runner.update(sql, product.getName(), product.getDescription(), product.getOriginalPrice(),
                product.getStock(), product.getDiscountStrategyId(), product.getId(), product.getSellerId());
    }

    public void updateDiscount(int productId, int sellerId, int strategyId) throws Exception {
        runner.update("UPDATE product SET discount_strategy_id = ? WHERE id = ? AND seller_id = ?",
                strategyId, productId, sellerId);
    }

    public void deleteBySeller(int productId, int sellerId) throws Exception {
        runner.update("DELETE FROM product WHERE id = ? AND seller_id = ?", productId, sellerId);
    }

    public void deleteById(int productId) throws Exception {
        runner.update("DELETE FROM product WHERE id = ?", productId);
    }

    public long countAll() throws Exception {
        Number value = runner.query("SELECT COUNT(*) FROM product", new ScalarHandler<>());
        return value.longValue();
    }

    public void decreaseStock(Connection connection, int productId, int quantity) throws Exception {
        QueryRunner transactionRunner = new QueryRunner();
        int affected = transactionRunner.update(connection,
                "UPDATE product SET stock = stock - ? WHERE id = ? AND stock >= ?",
                quantity, productId, quantity);
        if (affected == 0) {
            throw new IllegalStateException("商品库存不足");
        }
    }
}
