<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.CartItem, java.util.Map, java.math.BigDecimal" %>
<html>
<head>
    <title>购物车</title>
    <style>
        body { font-family: Arial, "Microsoft YaHei", sans-serif; background: #f5f7fb; margin: 0; padding: 32px; }
        .wrap { max-width: 1100px; margin: auto; background: white; padding: 24px; border-radius: 8px; box-shadow: 0 8px 24px rgba(15,23,42,.08); }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 12px; border-bottom: 1px solid #e5e7eb; text-align: left; }
        img { width: 78px; height: 78px; object-fit: cover; border-radius: 6px; }
        input[type=number] { width: 70px; padding: 7px; }
        button, .btn { border: 0; border-radius: 6px; padding: 9px 14px; background: #2563eb; color: white; text-decoration: none; cursor: pointer; }
        .danger { background: #dc2626; }
        .total { text-align: right; font-size: 22px; font-weight: 800; margin-top: 18px; }
        .msg { padding: 10px; background: #fee2e2; color: #991b1b; border-radius: 6px; margin-bottom: 12px; }
    </style>
</head>
<body>
<div class="wrap">
    <h2>我的购物车</h2>
    <% if (request.getAttribute("msg") != null) { %><div class="msg"><%= request.getAttribute("msg") %></div><% } %>
    <a class="btn" href="${pageContext.request.contextPath}/buyer/products">继续购物</a>
    <br><br>
    <table>
        <tr><th>图片</th><th>商品</th><th>原价</th><th>折后价</th><th>数量</th><th>小计</th><th>操作</th></tr>
        <% Map<Integer, CartItem> cart = (Map<Integer, CartItem>) request.getAttribute("cart");
           if (cart != null && !cart.isEmpty()) { for (CartItem item : cart.values()) { %>
        <tr>
            <td><img src="${pageContext.request.contextPath}/<%= item.getProduct().getImagePath() %>" onerror="this.style.display='none'"></td>
            <td><%= item.getProduct().getName() %></td>
            <td>¥<%= item.getProduct().getOriginalPrice() %></td>
            <td>¥<%= item.getFinalPrice() %></td>
            <td>
                <form action="${pageContext.request.contextPath}/buyer/cart" method="post">
                    <input type="hidden" name="productId" value="<%= item.getProduct().getId() %>">
                    <input type="number" name="quantity" min="0" value="<%= item.getQuantity() %>">
                    <button type="submit">更新</button>
                </form>
            </td>
            <td>¥<%= item.getSubtotal() %></td>
            <td>
                <form action="${pageContext.request.contextPath}/buyer/cart" method="post">
                    <input type="hidden" name="productId" value="<%= item.getProduct().getId() %>">
                    <input type="hidden" name="quantity" value="0">
                    <button class="danger" type="submit">删除</button>
                </form>
            </td>
        </tr>
        <% }} else { %>
            <tr><td colspan="7">购物车为空。</td></tr>
        <% } %>
    </table>
    <% BigDecimal total = (BigDecimal) request.getAttribute("total"); %>
    <div class="total">总计：¥<%= total == null ? "0.00" : total %></div>
    <form action="${pageContext.request.contextPath}/buyer/order" method="post" style="text-align:right;">
        <button type="submit">提交订单</button>
    </form>
</div>
</body>
</html>
