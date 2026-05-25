package com.shop.controller;

import com.shop.entity.User;
import com.shop.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/seller/products", "/seller/manage", "/seller/deleteProduct", "/seller/discount"})
public class SellerProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User seller = (User) req.getSession().getAttribute("loginUser");
            if (req.getServletPath().endsWith("deleteProduct")) {
                productService.deleteForSeller(Integer.parseInt(req.getParameter("id")), seller.getId());
                resp.sendRedirect(req.getContextPath() + "/seller/products");
                return;
            }
            req.setAttribute("products", productService.findBySeller(seller.getId()));
            req.setAttribute("strategies", productService.findStrategies());
            req.getRequestDispatcher("/seller/manageProducts.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            User seller = (User) req.getSession().getAttribute("loginUser");
            productService.updateDiscount(Integer.parseInt(req.getParameter("productId")), seller.getId(),
                    Integer.parseInt(req.getParameter("discountStrategyId")));
            resp.sendRedirect(req.getContextPath() + "/seller/products");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
