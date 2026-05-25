package com.shop.controller;
import com.shop.dao.ProductDao;
import com.shop.entity.CartItem;
import com.shop.entity.Product;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/buyer/cart")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter("action");
            HttpSession session = req.getSession();
            Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
            if (cart == null) { cart = new HashMap<>(); session.setAttribute("cart", cart); }

            if ("add".equals(action)) {
                int productId = Integer.parseInt(req.getParameter("productId"));
                if (cart.containsKey(productId)) {
                    cart.get(productId).setQuantity(cart.get(productId).getQuantity() + 1);
                } else {
                    Product p = new ProductDao().findById(productId);
                    cart.put(productId, new CartItem(p, 1));
                }
                resp.sendRedirect(req.getContextPath() + "/buyer/cart.jsp");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}