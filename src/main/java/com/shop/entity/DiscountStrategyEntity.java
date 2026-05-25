package com.shop.entity;

import java.math.BigDecimal;

public class DiscountStrategyEntity {
    private int id;
    private String strategyName;
    private String strategyClass;
    private BigDecimal discountValue;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getStrategyName() { return strategyName; }
    public void setStrategyName(String strategyName) { this.strategyName = strategyName; }
    public String getStrategyClass() { return strategyClass; }
    public void setStrategyClass(String strategyClass) { this.strategyClass = strategyClass; }
    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }
}
