package com.shop.filter;
import com.shop.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/buyer/*", "/seller/*", "/admin/*"})
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        User user = (User) req.getSession().getAttribute("loginUser");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String uri = req.getRequestURI();
        if (uri.contains("/buyer/") && !"buyer".equals(user.getRole())) {
            resp.getWriter().write("Access Denied: Buyers only."); return;
        }
        if (uri.contains("/seller/") && !"seller".equals(user.getRole())) {
            resp.getWriter().write("Access Denied: Sellers only."); return;
        }
        chain.doFilter(req, resp);
    }
}