package com.shop.controller;

import com.shop.entity.User;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String path = req.getPathInfo() == null ? "/dashboard" : req.getPathInfo();
            if ("/deleteUser".equals(path)) {
                User current = (User) req.getSession().getAttribute("loginUser");
                userService.delete(Integer.parseInt(req.getParameter("id")), current.getId());
                resp.sendRedirect(req.getContextPath() + "/admin/users");
                return;
            }
            if ("/deleteProduct".equals(path)) {
                productService.deleteAsAdmin(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(req.getContextPath() + "/admin/products");
                return;
            }
            if ("/users".equals(path)) {
                req.setAttribute("users", userService.findAll());
                req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
                return;
            }
            if ("/products".equals(path)) {
                req.setAttribute("products", productService.findAll());
                req.getRequestDispatcher("/admin/products.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("buyerCount", userService.countByRole("buyer"));
            req.setAttribute("sellerCount", userService.countByRole("seller"));
            req.setAttribute("adminCount", userService.countByRole("admin"));
            req.setAttribute("productCount", productService.countAll());
            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
