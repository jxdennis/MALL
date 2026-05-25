package com.shop.controller;

import com.shop.dao.ProductDao; // 🚨 核心：必须导入你刚修好的 Dao 层
import com.shop.entity.Product; // 🚨 核心：必须导入商品实体类
import com.shop.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/seller/addProduct")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. 接收普通文本数据
            String name = req.getParameter("name");
            String category = req.getParameter("category");
            String description = req.getParameter("description");
            double originalPrice = Double.parseDouble(req.getParameter("originalPrice"));
            int stock = Integer.parseInt(req.getParameter("stock"));
            int strategyId = Integer.parseInt(req.getParameter("discountStrategyId"));

            // 2. 接收并处理图片文件
            Part imagePart = req.getPart("image");
            String newFileName = UUID.randomUUID().toString() + "_" + imagePart.getSubmittedFileName();
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            imagePart.write(uploadPath + File.separator + newFileName);
            String dbImagePath = "uploads/" + newFileName;

            // 3. 获取当前登录商家的 Session 信息
            User seller = (User) req.getSession().getAttribute("loginUser");

            // ==================== 🚨 这里就是第二步替换进来的代码 ====================
            // 🌟 规范写法：把散乱的数据组装成一个干净的商品对象 (Product)
            Product newProduct = new Product();
            newProduct.setSellerId(seller.getId());
            newProduct.setName(name);
            newProduct.setCategory(category);
            newProduct.setDescription(description);
            newProduct.setOriginalPrice(originalPrice);
            newProduct.setStock(stock);
            newProduct.setImagePath(dbImagePath);
            newProduct.setDiscountStrategyId(strategyId);

            // 🌟 优雅调用：实例化你刚写好 addProduct 的 ProductDao，把对象扔进去保存
            ProductDao productDao = new ProductDao();
            productDao.addProduct(newProduct);
            // ====================================================================

            // 4. 发布成功，跳转回商家控制台首页
            resp.sendRedirect(req.getContextPath() + "/seller/index.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<script>alert('商品发布失败！'); history.back();</script>");
        }
    }
}