package com.shop.controller;

import com.shop.entity.User;
import com.shop.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buyer/order")
public class OrderServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User buyer = (User) req.getSession().getAttribute("loginUser");
            String orderNo = orderService.checkout(buyer, req.getSession());
            req.setAttribute("orderNo", orderNo);
            req.getRequestDispatcher("/buyer/orderSuccess.jsp").forward(req, resp);
        } catch (Exception e) {
            req.getSession().setAttribute("cartMsg", e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/buyer/cart");
        }
    }
}
