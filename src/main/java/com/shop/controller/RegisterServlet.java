package com.shop.controller;
import com.shop.dao.UserDao;
import com.shop.entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User u = new User();
            u.setUsername(req.getParameter("username"));
            u.setPassword(req.getParameter("password")); // 实际需MD5加密
            u.setRole(req.getParameter("role"));
            u.setIdCard(req.getParameter("idCard"));
            u.setProvince(Integer.parseInt(req.getParameter("province")));
            u.setCity(Integer.parseInt(req.getParameter("city")));
            u.setDistrict(Integer.parseInt(req.getParameter("district")));

            // 后端二次正则校验
            if (!u.getIdCard().matches("^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[\\dX]$") ||
                    !u.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                req.setAttribute("msg", "数据格式非法！");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }

            new UserDao().addUser(u);
            resp.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}