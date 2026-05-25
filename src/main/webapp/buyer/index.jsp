<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.Product, com.shop.entity.User, com.shop.util.PriceUtil, java.util.List" %>
<html>
<head>
    <title>买家商城</title>
    <style>
        body { margin: 0; font-family: Arial, "Microsoft YaHei", sans-serif; background: #f5f7fb; color: #111827; }
        .nav { background: #111827; color: white; padding: 18px 40px; display: flex; justify-content: space-between; align-items: center; }
        .nav a { color: white; text-decoration: none; margin-left: 14px; padding: 8px 12px; background: #2563eb; border-radius: 6px; }
        .wrap { padding: 28px 40px; }
        .grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); gap: 20px; }
        .item { background: white; border-radius: 8px; padding: 16px; box-shadow: 0 8px 24px rgba(15,23,42,.06); }
        .item img { width: 100%; height: 180px; object-fit: cover; border-radius: 6px; background: #e5e7eb; }
        .desc { color: #64748b; height: 44px; overflow: hidden; }
        .price { color: #dc2626; font-size: 22px; font-weight: 800; }
        .origin { color: #94a3b8; text-decoration: line-through; margin-right: 8px; }
        .btn { display: block; text-align: center; color: white; background: #16a34a; padding: 10px; border-radius: 6px; text-decoration: none; margin-top: 12px; font-weight: 700; }
    </style>
</head>
<body>
<% User user = (User) session.getAttribute("loginUser"); %>
<div class="nav">
    <div><strong>买家商城</strong>，欢迎 <%= user.getUsername() %></div>
    <div>
        <a href="${pageContext.request.contextPath}/buyer/cart">购物车</a>
        <a href="${pageContext.request.contextPath}/logout" style="background:#dc2626;">退出</a>
    </div>
</div>
<div class="wrap">
    <div class="grid">
        <% List<Product> products = (List<Product>) request.getAttribute("products");
           if (products == null || products.isEmpty()) { %>
            <p>暂无商品。</p>
        <% } else { for (Product p : products) { %>
        <div class="item">
            <img src="${pageContext.request.contextPath}/<%= p.getImagePath() %>" alt="商品图片" onerror="this.style.display='none'">
            <h3><%= p.getName() %></h3>
            <p class="desc"><%= p.getDescription() == null ? "暂无描述" : p.getDescription() %></p>
            <div><span class="origin">¥<%= p.getOriginalPrice() %></span><span class="price">¥<%= PriceUtil.getFinalPrice(p) %></span></div>
            <p>库存：<%= p.getStock() %>　策略：<%= p.getStrategyName() %></p>
            <a class="btn" href="${pageContext.request.contextPath}/buyer/cart?action=add&productId=<%= p.getId() %>">加入购物车</a>
        </div>
        <% }} %>
    </div>
</div>
</body>
</html>
