package com.shop.entity;

import com.shop.util.PriceUtil;

public class CartItem {
    private Product product; // 购买的商品对象
    private int quantity;    // 购买数量

    public CartItem() {
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 计算该条目商品的小计金额（折后价 * 数量）
     * 核心要求：在这里调用策略模式工具类获取真正售价
     */
    public double getSubtotal() {
        if (this.product != null) {
            // 调用 PriceUtil 根据策略类名和折扣值计算当前单价
            double finalPrice = PriceUtil.getFinalPrice(
                    product.getOriginalPrice(),
                    product.getStrategyClass(),
                    product.getDiscountValue()
            );
            // 单价 * 数量
            return finalPrice * quantity;
        }
        return 0.0;
    }
}