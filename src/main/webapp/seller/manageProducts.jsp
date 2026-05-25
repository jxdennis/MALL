<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.Product, com.shop.entity.DiscountStrategyEntity, java.util.List" %>
<html>
<head>
    <title>商家商品管理</title>
    <style>
        body { margin: 0; font-family: Arial, "Microsoft YaHei", sans-serif; background: #f5f7fb; padding: 32px; }
        .wrap { max-width: 1180px; margin: auto; background: white; border-radius: 8px; padding: 24px; box-shadow: 0 8px 24px rgba(15,23,42,.08); }
        .top { display: flex; justify-content: space-between; align-items: center; }
        table { width: 100%; border-collapse: collapse; margin-top: 18px; }
        th, td { border-bottom: 1px solid #e5e7eb; padding: 12px; text-align: left; }
        img { width: 70px; height: 70px; object-fit: cover; border-radius: 6px; }
        .btn { color: white; background: #2563eb; border: 0; border-radius: 6px; padding: 8px 12px; text-decoration: none; cursor: pointer; }
        .danger { background: #dc2626; }
        select { padding: 8px; border: 1px solid #cbd5e1; border-radius: 6px; }
    </style>
</head>
<body>
<div class="wrap">
    <div class="top">
        <h2>商家商品管理</h2>
        <div>
            <a class="btn" href="${pageContext.request.contextPath}/seller/addProduct">上传商品</a>
            <a class="btn danger" href="${pageContext.request.contextPath}/logout">退出</a>
        </div>
    </div>
    <table>
        <tr><th>图片</th><th>名称</th><th>原价</th><th>库存</th><th>折扣策略</th><th>操作</th></tr>
        <% List<Product> products = (List<Product>) request.getAttribute("products");
           List<DiscountStrategyEntity> strategies = (List<DiscountStrategyEntity>) request.getAttribute("strategies");
           if (products == null || products.isEmpty()) { %>
            <tr><td colspan="6">暂无商品。</td></tr>
        <% } else { for (Product p : products) { %>
        <tr>
            <td><img src="${pageContext.request.contextPath}/<%= p.getImagePath() %>" onerror="this.style.display='none'"></td>
            <td><%= p.getName() %><br><small><%= p.getDescription() == null ? "" : p.getDescription() %></small></td>
            <td>¥<%= p.getOriginalPrice() %></td>
            <td><%= p.getStock() %></td>
            <td>
                <form action="${pageContext.request.contextPath}/seller/discount" method="post">
                    <input type="hidden" name="productId" value="<%= p.getId() %>">
                    <select name="discountStrategyId">
                        <% if (strategies != null) { for (DiscountStrategyEntity s : strategies) { %>
                            <option value="<%= s.getId() %>" <%= s.getId() == p.getDiscountStrategyId() ? "selected" : "" %>><%= s.getStrategyName() %></option>
                        <% }} %>
                    </select>
                    <button class="btn" type="submit">保存</button>
                </form>
            </td>
            <td><a class="btn danger" onclick="return confirm('确定删除该商品？')" href="${pageContext.request.contextPath}/seller/deleteProduct?id=<%= p.getId() %>">删除</a></td>
        </tr>
        <% }} %>
    </table>
</div>
</body>
</html>
