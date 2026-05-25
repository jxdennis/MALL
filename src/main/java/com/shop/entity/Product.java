package com.shop.entity;

public class Product {
    private int id;
    private int sellerId;
    private String name;
    private String category; // 🆕 新增：商品分类
    private String description;
    private double originalPrice;
    private int stock;
    private String imagePath;
    private int discountStrategyId;

    // 联表查询带出的折扣策略字段
    private String strategyClass;
    private double discountValue;

    // --- 快捷生成 Getter 和 Setter ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(double originalPrice) { this.originalPrice = originalPrice; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public int getDiscountStrategyId() { return discountStrategyId; }
    public void setDiscountStrategyId(int discountStrategyId) { this.discountStrategyId = discountStrategyId; }
    public String getStrategyClass() { return strategyClass; }
    public void setStrategyClass(String strategyClass) { this.strategyClass = strategyClass; }
    public double getDiscountValue() { return discountValue; }
    public void setDiscountValue(double discountValue) { this.discountValue = discountValue; }
}