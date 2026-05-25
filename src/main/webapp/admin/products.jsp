<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.Product, java.util.List" %>
<html>
<head>
    <title>商品管理</title>
    <style>
        body { font-family: Arial, "Microsoft YaHei", sans-serif; background:#f5f7fb; padding:32px; }
        .wrap { max-width:1100px; margin:auto; background:white; padding:24px; border-radius:8px; box-shadow:0 8px 24px rgba(15,23,42,.08); }
        table { width:100%; border-collapse:collapse; }
        th, td { padding:12px; border-bottom:1px solid #e5e7eb; text-align:left; }
        img { width:68px; height:68px; object-fit:cover; border-radius:6px; }
        a { color:white; background:#2563eb; border-radius:6px; padding:8px 12px; text-decoration:none; }
        .danger { background:#dc2626; }
    </style>
</head>
<body>
<div class="wrap">
    <h2>商品管理</h2>
    <p><a href="${pageContext.request.contextPath}/admin/dashboard">返回看板</a></p>
    <table>
        <tr><th>图片</th><th>名称</th><th>商家</th><th>价格</th><th>库存</th><th>策略</th><th>操作</th></tr>
        <% List<Product> products = (List<Product>) request.getAttribute("products");
           if (products != null) { for (Product p : products) { %>
        <tr>
            <td><img src="${pageContext.request.contextPath}/<%= p.getImagePath() %>" onerror="this.style.display='none'"></td>
            <td><%= p.getName() %></td>
            <td><%= p.getSellerName() %></td>
            <td>¥<%= p.getOriginalPrice() %></td>
            <td><%= p.getStock() %></td>
            <td><%= p.getStrategyName() %></td>
            <td><a class="danger" onclick="return confirm('确定删除该商品？')" href="${pageContext.request.contextPath}/admin/deleteProduct?id=<%= p.getId() %>">删除</a></td>
        </tr>
        <% }} %>
    </table>
</div>
</body>
</html>
