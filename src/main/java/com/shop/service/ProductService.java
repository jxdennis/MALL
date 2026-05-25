package com.shop.service;

import com.shop.dao.DiscountStrategyDao;
import com.shop.dao.ProductDao;
import com.shop.entity.DiscountStrategyEntity;
import com.shop.entity.Product;
import java.util.List;

public class ProductService {
    private final ProductDao productDao = new ProductDao();
    private final DiscountStrategyDao strategyDao = new DiscountStrategyDao();

    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    public List<Product> findBySeller(int sellerId) throws Exception {
        return productDao.findBySellerId(sellerId);
    }

    public Product findById(int id) throws Exception {
        return productDao.findById(id);
    }

    public List<DiscountStrategyEntity> findStrategies() throws Exception {
        return strategyDao.findAll();
    }

    public void add(Product product) throws Exception {
        validate(product);
        productDao.add(product);
    }

    public void update(Product product) throws Exception {
        validate(product);
        productDao.update(product);
    }

    public void updateDiscount(int productId, int sellerId, int strategyId) throws Exception {
        productDao.updateDiscount(productId, sellerId, strategyId);
    }

    public void deleteForSeller(int productId, int sellerId) throws Exception {
        productDao.deleteBySeller(productId, sellerId);
    }

    public void deleteAsAdmin(int productId) throws Exception {
        productDao.deleteById(productId);
    }

    public long countAll() throws Exception {
        return productDao.countAll();
    }

    private void validate(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("商品名称不能为空");
        }
        if (product.getOriginalPrice() == null || product.getOriginalPrice().signum() <= 0) {
            throw new IllegalArgumentException("商品原价必须大于 0");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("库存不能小于 0");
        }
        if (product.getDiscountStrategyId() <= 0) {
            throw new IllegalArgumentException("请选择折扣策略");
        }
    }
}
