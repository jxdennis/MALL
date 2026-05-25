package com.shop.dao;

import com.shop.entity.Product;
import com.shop.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.util.List;

public class ProductDao {

    // 1. 查询所有商品 (展厅用)
    public List<Product> findAll() throws Exception {
        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "SELECT p.id, p.seller_id AS sellerId, p.name, p.category, p.description, p.original_price AS originalPrice, p.stock, p.image_path AS imagePath, p.discount_strategy_id AS discountStrategyId, s.strategy_class AS strategyClass, s.discount_value AS discountValue FROM product p LEFT JOIN discount_strategy s ON p.discount_strategy_id = s.id ORDER BY p.id DESC";
        return qr.query(sql, new BeanListHandler<>(Product.class));
    }

    // 2. 根据ID查询单个 (购物车用)
    public Product findById(int id) throws Exception {
        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "SELECT p.id, p.seller_id AS sellerId, p.name, p.category, p.description, p.original_price AS originalPrice, p.stock, p.image_path AS imagePath, p.discount_strategy_id AS discountStrategyId, s.strategy_class AS strategyClass, s.discount_value AS discountValue FROM product p LEFT JOIN discount_strategy s ON p.discount_strategy_id = s.id WHERE p.id = ?";
        return qr.query(sql, new BeanHandler<>(Product.class), id);
    }

    // 3. 根据商家ID查询列表【🆕 带翻页功能】
    public List<Product> findBySellerId(int sellerId, int offset, int pageSize) throws Exception {
        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "SELECT p.id, p.seller_id AS sellerId, p.name, p.category, p.description, p.original_price AS originalPrice, p.stock, p.image_path AS imagePath, p.discount_strategy_id AS discountStrategyId, s.strategy_class AS strategyClass, s.discount_value AS discountValue FROM product p LEFT JOIN discount_strategy s ON p.discount_strategy_id = s.id WHERE p.seller_id = ? ORDER BY p.id DESC LIMIT ?, ?";
        return qr.query(sql, new BeanListHandler<>(Product.class), sellerId, offset, pageSize);
    }

    // 4. 删除商品
    public void deleteById(int id) throws Exception {
        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "DELETE FROM product WHERE id = ?";
        qr.update(sql, id);
    }
    /**
     * 5. 发布新商品（将 Servlet 中的 SQL 抽离到这里）
     */
    public void addProduct(Product p) throws Exception {
        QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "INSERT INTO product (seller_id, name, category, description, original_price, stock, image_path, discount_strategy_id) VALUES (?,?,?,?,?,?,?,?)";

        qr.update(sql,
                p.getSellerId(),
                p.getName(),
                p.getCategory(),
                p.getDescription(),
                p.getOriginalPrice(),
                p.getStock(),
                p.getImagePath(),
                p.getDiscountStrategyId()
        );
    }
}