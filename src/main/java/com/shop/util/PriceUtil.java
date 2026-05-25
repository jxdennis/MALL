package com.shop.util;

import com.shop.entity.Product;
import com.shop.strategy.DiscountStrategy;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceUtil {
    private PriceUtil() {}

    public static BigDecimal getFinalPrice(Product product) {
        if (product == null) {
            return BigDecimal.ZERO;
        }
        return getFinalPrice(product.getOriginalPrice(), product.getStrategyClass(), product.getDiscountValue());
    }

    public static BigDecimal getFinalPrice(BigDecimal originalPrice, String strategyClass, BigDecimal discountValue) {
        if (originalPrice == null) {
            return BigDecimal.ZERO;
        }
        if (strategyClass == null || strategyClass.isBlank()) {
            return originalPrice.setScale(2, RoundingMode.HALF_UP);
        }
        try {
            DiscountStrategy strategy = (DiscountStrategy) Class.forName(strategyClass).getDeclaredConstructor().newInstance();
            BigDecimal value = discountValue == null ? BigDecimal.ONE : discountValue;
            return strategy.calculate(originalPrice, value).setScale(2, RoundingMode.HALF_UP);
        } catch (ReflectiveOperationException | ClassCastException e) {
            return originalPrice.setScale(2, RoundingMode.HALF_UP);
        }
    }
}
