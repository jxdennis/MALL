package com.shop.controller;

import com.shop.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkUser")
public class CheckUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/plain;charset=UTF-8");
            resp.getWriter().write(userService.exists(req.getParameter("username")) ? "exists" : "ok");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
