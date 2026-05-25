package com.shop.strategy;

import java.math.BigDecimal;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal calculate(BigDecimal originalPrice, BigDecimal discountValue) {
        return originalPrice;
    }
}
