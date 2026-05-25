package com.shop.filter;

import com.shop.entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/buyer/*", "/seller/*", "/admin/*"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        User user = (User) req.getSession().getAttribute("loginUser");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        String uri = req.getRequestURI();
        if (uri.contains("/buyer/") && !"buyer".equals(user.getRole())) {
            deny(resp, "仅买家可访问该页面");
            return;
        }
        if (uri.contains("/seller/") && !"seller".equals(user.getRole())) {
            deny(resp, "仅商家可访问该页面");
            return;
        }
        if (uri.contains("/admin/") && !"admin".equals(user.getRole())) {
            deny(resp, "仅管理员可访问该页面");
            return;
        }
        chain.doFilter(request, response);
    }

    private void deny(HttpServletResponse resp, String message) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<h2>" + message + "</h2><a href='../login.jsp'>返回登录页</a>");
    }
}
