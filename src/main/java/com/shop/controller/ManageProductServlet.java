package com.shop.controller;

import com.shop.dao.ProductDao;
import com.shop.entity.Product;
import com.shop.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/seller/manage")
public class ManageProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User seller = (User) req.getSession().getAttribute("loginUser");
            ProductDao productDao = new ProductDao();
            String action = req.getParameter("action");

            // 处理删除
            if ("delete".equals(action)) {
                int productId = Integer.parseInt(req.getParameter("id"));
                productDao.deleteById(productId);
                resp.sendRedirect(req.getContextPath() + "/seller/manage");
                return;
            }

            // --- 🆕 翻页核心逻辑 ---
            int pageNo = 1; // 默认第1页
            int pageSize = 5; // 每页显示5条
            String pageParam = req.getParameter("pageNo");
            if (pageParam != null && !pageParam.isEmpty()) {
                pageNo = Integer.parseInt(pageParam);
                if (pageNo < 1) pageNo = 1; // 防止变成负数
            }
            int offset = (pageNo - 1) * pageSize; // 计算偏移量

            // 拉取分页数据
            List<Product> productList = productDao.findBySellerId(seller.getId(), offset, pageSize);

            // 将当前页码和数据发给页面
            req.setAttribute("productList", productList);
            req.setAttribute("currentPage", pageNo);

            req.getRequestDispatcher("/seller/manageProducts.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("<script>alert('系统异常'); history.back();</script>");
        }
    }
}