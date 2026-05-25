package com.shop.controller;
import com.shop.dao.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/checkUser")
public class CheckUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String username = req.getParameter("username");
            boolean exists = (new UserDao().findByUsername(username) != null);
            resp.getWriter().write(exists ? "exists" : "ok");
        } catch (Exception e) { e.printStackTrace(); }
    }
}