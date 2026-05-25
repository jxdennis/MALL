package com.shop.controller;

import com.shop.dao.UserDao;
import com.shop.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("username");
        String pword = req.getParameter("password");

        try {
            UserDao userDao = new UserDao();
            User user = userDao.login(uname, pword);

            if (user != null) {
                req.getSession().setAttribute("loginUser", user);

                // 🚨 修复点：强制拼接 getContextPath() 实现跨目录跳转
                if ("buyer".equals(user.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/buyer/index.jsp");
                } else if ("seller".equals(user.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/seller/index.jsp");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/login.jsp");
                }
            } else {
                req.setAttribute("msg", "用户名或密码输入错误，请重新输入！");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "系统数据库连接发生异常！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}