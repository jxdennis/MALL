package com.shop.strategy;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal calculate(BigDecimal originalPrice, BigDecimal discountValue);
}
