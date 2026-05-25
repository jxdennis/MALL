package com.shop.strategy;
public interface DiscountStrategy {
    double calculate(double originalPrice, double discountValue);
}