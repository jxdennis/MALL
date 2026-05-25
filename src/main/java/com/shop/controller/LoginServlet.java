package com.shop.controller;

import com.shop.entity.User;
import com.shop.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = userService.login(req.getParameter("username"), req.getParameter("password"));
            if (user == null) {
                req.setAttribute("msg", "用户名或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
            req.getSession().setAttribute("loginUser", user);
            if ("buyer".equals(user.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/buyer/products");
            } else if ("seller".equals(user.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/seller/products");
            } else {
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            }
        } catch (Exception e) {
            req.setAttribute("msg", "登录失败：" + e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
