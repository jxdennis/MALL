package com.shop.util;
import com.shop.strategy.DiscountStrategy;

public class PriceUtil {
    public static double getFinalPrice(double originalPrice, String strategyClass, double discountValue) {
        try {
            // 反射实例化策略对象
            DiscountStrategy strategy = (DiscountStrategy) Class.forName(strategyClass).getDeclaredConstructor().newInstance();
            return strategy.calculate(originalPrice, discountValue);
        } catch (Exception e) {
            e.printStackTrace();
            return originalPrice; // 发生异常则原价返回
        }
    }
}