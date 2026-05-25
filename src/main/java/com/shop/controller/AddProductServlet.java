package com.shop.controller;

import com.shop.entity.Product;
import com.shop.entity.User;
import com.shop.service.ProductService;
import com.shop.util.UploadUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/seller/addProduct")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class AddProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("strategies", productService.findStrategies());
            req.getRequestDispatcher("/seller/addProduct.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User seller = (User) req.getSession().getAttribute("loginUser");
            Product product = new Product();
            product.setSellerId(seller.getId());
            product.setName(req.getParameter("name"));
            product.setDescription(req.getParameter("description"));
            product.setOriginalPrice(new BigDecimal(req.getParameter("originalPrice")));
            product.setStock(Integer.parseInt(req.getParameter("stock")));
            product.setDiscountStrategyId(Integer.parseInt(req.getParameter("discountStrategyId")));
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            product.setImagePath(UploadUtil.saveImage(req.getPart("image"), uploadPath));
            productService.add(product);
            resp.sendRedirect(req.getContextPath() + "/seller/products");
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            doGet(req, resp);
        }
    }
}
