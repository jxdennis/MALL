package com.shop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private int id;
    private int sellerId;
    private String sellerName;
    private String name;
    private String description;
    private BigDecimal originalPrice;
    private int stock;
    private String imagePath;
    private int discountStrategyId;
    private String strategyName;
    private String strategyClass;
    private BigDecimal discountValue;
    private LocalDateTime createTime;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }
    public String getSellerName() { return sellerName; }
    public void setSellerName(String sellerName) { this.sellerName = sellerName; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(BigDecimal originalPrice) { this.originalPrice = originalPrice; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public int getDiscountStrategyId() { return discountStrategyId; }
    public void setDiscountStrategyId(int discountStrategyId) { this.discountStrategyId = discountStrategyId; }
    public String getStrategyName() { return strategyName; }
    public void setStrategyName(String strategyName) { this.strategyName = strategyName; }
    public String getStrategyClass() { return strategyClass; }
    public void setStrategyClass(String strategyClass) { this.strategyClass = strategyClass; }
    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
