package com.shop.controller;

import com.shop.service.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buyer/cart")
public class CartServlet extends HttpServlet {
    private final CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
                cartService.add(req.getSession(), Integer.parseInt(req.getParameter("productId")));
                resp.sendRedirect(req.getContextPath() + "/buyer/cart");
                return;
            }
            Object flash = req.getSession().getAttribute("cartMsg");
            if (flash != null) {
                req.setAttribute("msg", flash);
                req.getSession().removeAttribute("cartMsg");
            }
            req.setAttribute("cart", cartService.getCart(req.getSession()));
            req.setAttribute("total", cartService.total(cartService.getCart(req.getSession())));
            req.getRequestDispatcher("/buyer/cart.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            req.setAttribute("cart", cartService.getCart(req.getSession()));
            req.setAttribute("total", cartService.total(cartService.getCart(req.getSession())));
            req.getRequestDispatcher("/buyer/cart.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        cartService.update(req.getSession(), productId, quantity);
        resp.sendRedirect(req.getContextPath() + "/buyer/cart");
    }
}
