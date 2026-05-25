<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.DiscountStrategyEntity, java.util.List" %>
<html>
<head>
    <title>上传商品</title>
    <style>
        body { margin: 0; font-family: Arial, "Microsoft YaHei", sans-serif; background: #f5f7fb; padding: 32px; }
        .box { max-width: 680px; margin: auto; background: white; border-radius: 8px; padding: 26px; box-shadow: 0 8px 24px rgba(15,23,42,.08); }
        label { display: block; margin: 14px 0 6px; font-weight: 700; }
        input, textarea, select { width: 100%; box-sizing: border-box; padding: 10px; border: 1px solid #cbd5e1; border-radius: 6px; }
        button { margin-top: 20px; padding: 12px 18px; border: 0; border-radius: 6px; background: #2563eb; color: white; font-weight: 700; }
        .msg { padding: 10px; border-radius: 6px; background: #fee2e2; color: #991b1b; }
    </style>
</head>
<body>
<div class="box">
    <h2>上传商品</h2>
    <% if (request.getAttribute("msg") != null) { %><div class="msg"><%= request.getAttribute("msg") %></div><% } %>
    <form action="${pageContext.request.contextPath}/seller/addProduct" method="post" enctype="multipart/form-data">
        <label>商品名称</label><input name="name" required>
        <label>商品描述</label><textarea name="description" rows="4"></textarea>
        <label>原价</label><input type="number" step="0.01" min="0.01" name="originalPrice" required>
        <label>库存</label><input type="number" min="0" name="stock" required>
        <label>商品图片</label><input type="file" name="image" accept="image/*" required>
        <label>折扣策略</label>
        <select name="discountStrategyId">
            <% List<DiscountStrategyEntity> strategies = (List<DiscountStrategyEntity>) request.getAttribute("strategies");
               if (strategies != null) { for (DiscountStrategyEntity s : strategies) { %>
                <option value="<%= s.getId() %>"><%= s.getStrategyName() %></option>
            <% }} %>
        </select>
        <button type="submit">发布商品</button>
        <a href="${pageContext.request.contextPath}/seller/products" style="margin-left:14px;color:#2563eb;">返回商品管理</a>
    </form>
</div>
</body>
</html>
