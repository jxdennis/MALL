package com.shop.service;

import com.shop.dao.OrderDao;
import com.shop.entity.CartItem;
import com.shop.entity.User;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class OrderService {
    private final CartService cartService = new CartService();
    private final OrderDao orderDao = new OrderDao();

    public String checkout(User buyer, HttpSession session) throws Exception {
        Map<Integer, CartItem> cart = cartService.getCart(session);
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("购物车为空，无法下单");
        }
        BigDecimal total = cartService.total(cart);
        String orderNo = "OM" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now())
                + ThreadLocalRandom.current().nextInt(1000, 9999);
        orderDao.createOrder(buyer.getId(), orderNo, total, cart.values());
        cart.clear();
        return orderNo;
    }
}
