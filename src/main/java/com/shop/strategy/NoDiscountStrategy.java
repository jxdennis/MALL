package com.shop.strategy;
public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculate(double originalPrice, double discountValue) {
        return originalPrice;
    }
}