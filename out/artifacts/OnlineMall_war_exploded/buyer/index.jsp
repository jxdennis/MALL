<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.dao.ProductDao, com.shop.entity.Product, com.shop.entity.User" %>
<%@ page import="com.shop.util.PriceUtil, java.util.List" %>
<html>
<head>
    <title>商城首页 - 商品展厅</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 20px; background-color: #f8f9fa; }
        .navbar { background-color: #343a40; color: white; padding: 15px 30px; border-radius: 6px; display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }
        .navbar h2 { margin: 0; font-size: 22px; }
        .navbar .user-info { font-size: 15px; }
        .navbar .user-info strong { color: #ffc107; }
        .navbar a { color: #fff; text-decoration: none; margin-left: 20px; background-color: #007bff; padding: 8px 15px; border-radius: 4px; font-weight: bold; }
        .navbar a:hover { background-color: #0056b3; }
        .navbar .logout { background-color: #dc3545; }
        .navbar .logout:hover { background-color: #bd2130; }
        .product-grid { display: flex; flex-wrap: wrap; gap: 25px; justify-content: center; }
        .product-card { background: white; border: 1px solid #e2e8f0; border-radius: 8px; width: 280px; padding: 15px; box-shadow: 0 4px 6px rgba(0,0,0,0.02); display: flex; flex-direction: column; justify-content: space-between; transition: transform 0.2s; }
        .product-card:hover { transform: translateY(-5px); box-shadow: 0 6px 12px rgba(0,0,0,0.08); }
        .product-img { width: 100%; height: 180px; object-fit: cover; border-radius: 6px; margin-bottom: 12px; background-color: #eee; }
        .product-name { font-size: 18px; font-weight: bold; color: #1a202c; margin: 0 0 8px 0; }
        .product-desc { font-size: 14px; color: #718096; height: 40px; overflow: hidden; text-overflow: ellipsis; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; margin-bottom: 15px; line-height: 1.4; }
        .price-box { border-top: 1px dashed #e2e8f0; padding-top: 10px; margin-bottom: 15px; }
        .original-price { color: #a0aec0; text-decoration: line-through; font-size: 13px; }
        .discount-price { color: #e53e3e; font-size: 20px; font-weight: bold; margin-left: 5px; }
        .cart-btn { display: block; text-align: center; background-color: #28a745; color: white; padding: 10px; text-decoration: none; border-radius: 4px; font-weight: bold; font-size: 15px; }
        .cart-btn:hover { background-color: #218838; }
        .empty-tips { text-align: center; color: #718096; font-size: 16px; margin-top: 50px; width: 100%; }
    </style>
</head>
<body>
<%
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<div class="navbar">
    <h2>🏬 网上商城展厅中心</h2>
    <a href="${pageContext.request.contextPath}/index.jsp" style="background-color: #17a2b8;">🏠 返回大门首页</a>
    <div class="user-info">
        <span>当前身份：<strong><%= loginUser.getUsername() %></strong> (<%= loginUser.getRole() %>)</span>
        <a href="${pageContext.request.contextPath}/buyer/cart.jsp">🛒 查看购物车</a>
        <a href="${pageContext.request.contextPath}/login.jsp" class="logout">安全退出</a>
    </div>
</div>

<div class="product-grid">
    <%
        try {
            ProductDao productDao = new ProductDao();
            List<Product> list = productDao.findAll();

            if (list == null || list.isEmpty()) {
    %>
    <div class="empty-tips">💡 展厅货架空空如也，老板们还没开始发力上货哦！</div>
    <%
    } else {
        for (Product p : list) {
            double finalPrice = PriceUtil.getFinalPrice(
                    p.getOriginalPrice(),
                    p.getStrategyClass(),
                    p.getDiscountValue()
            );
    %>
    <div class="product-card">
        <div>
            <img class="product-img" src="${pageContext.request.contextPath}/<%= p.getImagePath() %>" onerror="this.src='https://placehold.co/280x180?text=No+Image'">
            <div class="product-name"><%= p.getName() %></div>
            <div class="product-desc"><%= p.getDescription() != null ? p.getDescription() : "该商品暂无描述。" %></div>
        </div>
        <div>
            <div class="price-box">
                <span class="original-price">原价: ¥<%= p.getOriginalPrice() %></span>
                <div>售价: <span class="discount-price">¥<%= String.format("%.2f", finalPrice) %></span></div>
            </div>
            <a class="cart-btn" href="${pageContext.request.contextPath}/buyer/cart?action=add&productId=<%= p.getId() %>">加入购物车</a>
        </div>
    </div>
    <%
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    %>
    <div style="color: red; text-align: center; width: 100%;">❌ 商品加载异常，请检查 ProductDao 的驼峰映射是否正确配置！</div>
    <%
        }
    %>
</div>
</body>
</html>