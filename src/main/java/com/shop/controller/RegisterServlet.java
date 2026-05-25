package com.shop.controller;

import com.shop.entity.User;
import com.shop.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User();
            user.setUsername(req.getParameter("username"));
            user.setPassword(req.getParameter("password"));
            user.setRole(req.getParameter("role"));
            user.setIdCard(req.getParameter("idCard"));
            user.setProvince(Integer.parseInt(req.getParameter("province")));
            user.setCity(Integer.parseInt(req.getParameter("city")));
            user.setDistrict(Integer.parseInt(req.getParameter("district")));
            userService.register(user, req.getParameter("confirmPassword"));
            resp.sendRedirect(req.getContextPath() + "/login.jsp?registered=1");
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}
