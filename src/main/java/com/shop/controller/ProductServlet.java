package com.shop.controller;
import com.shop.dao.ProductDao;
import com.shop.entity.Product;
import com.shop.entity.User;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.util.UUID;

@WebServlet("/seller/product")
@MultipartConfig
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User seller = (User) req.getSession().getAttribute("loginUser");
            Product p = new Product();
            p.setSellerId(seller.getId());
            p.setName(req.getParameter("name"));
            p.setDescription(req.getParameter("description"));
            p.setOriginalPrice(Double.parseDouble(req.getParameter("originalPrice")));
            p.setStock(Integer.parseInt(req.getParameter("stock")));
            p.setDiscountStrategyId(Integer.parseInt(req.getParameter("strategyId")));

            // 图片上传逻辑
            Part part = req.getPart("image");
            String fileName = UUID.randomUUID().toString() + ".jpg";
            String uploadPath = req.getServletContext().getRealPath("/uploads/");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            part.write(uploadPath + File.separator + fileName);
            p.setImagePath("uploads/" + fileName);

            new ProductDao().addProduct(p);
            resp.sendRedirect(req.getContextPath() + "/seller/index.jsp"); // 重定向回商家首页
        } catch (Exception e) { e.printStackTrace(); }
    }
}