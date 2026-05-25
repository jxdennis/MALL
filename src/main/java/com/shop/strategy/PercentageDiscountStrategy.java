package com.shop.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal calculate(BigDecimal originalPrice, BigDecimal discountValue) {
        return originalPrice.multiply(discountValue).setScale(2, RoundingMode.HALF_UP);
    }
}
