package com.shop.service;

import com.shop.entity.CartItem;
import com.shop.entity.Product;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartService {
    public static final String CART_KEY = "cart";
    private final ProductService productService = new ProductService();

    @SuppressWarnings("unchecked")
    public Map<Integer, CartItem> getCart(HttpSession session) {
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute(CART_KEY);
        if (cart == null) {
            cart = new LinkedHashMap<>();
            session.setAttribute(CART_KEY, cart);
        }
        return cart;
    }

    public void add(HttpSession session, int productId) throws Exception {
        Product product = productService.findById(productId);
        if (product == null || product.getStock() <= 0) {
            throw new IllegalArgumentException("商品不存在或库存不足");
        }
        Map<Integer, CartItem> cart = getCart(session);
        CartItem item = cart.get(productId);
        if (item == null) {
            cart.put(productId, new CartItem(product, 1));
            return;
        }
        if (item.getQuantity() + 1 > product.getStock()) {
            throw new IllegalArgumentException("购物车数量超过库存");
        }
        item.setQuantity(item.getQuantity() + 1);
    }

    public void update(HttpSession session, int productId, int quantity) {
        Map<Integer, CartItem> cart = getCart(session);
        if (quantity <= 0) {
            cart.remove(productId);
        } else if (cart.containsKey(productId)) {
            cart.get(productId).setQuantity(quantity);
        }
    }

    public BigDecimal total(Map<Integer, CartItem> cart) {
        return cart.values().stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
