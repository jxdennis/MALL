<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.*, java.util.Map" %>
<%@ page import="com.shop.util.PriceUtil" %>
<html>
<body>
<h2>我的购物车</h2>
<a href="${pageContext.request.contextPath}/buyer/index.jsp" style="margin-left: 20px; font-size: 16px; color: #007bff; text-decoration: none;">⬅️ 继续购物 (返回大厅)</a>
<table border="1">
    <tr><th>商品图片</th><th>商品名</th><th>原价</th><th>折后价</th><th>数量</th><th>小计</th></tr>
    <%
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cart != null) {
            for(CartItem item : cart.values()) {
                Product p = item.getProduct();
                double finalPrice = PriceUtil.getFinalPrice(p.getOriginalPrice(), p.getStrategyClass(), p.getDiscountValue());
                total += item.getSubtotal();
    %>
    <tr>
        <td><img src="${pageContext.request.contextPath}/<%= p.getImagePath() %>" width="80" height="80">
        <td><%= p.getName() %></td>
        <td><del><%= p.getOriginalPrice() %></del></td>
        <td><font color="red"><%= finalPrice %></font></td>
        <td><%= item.getQuantity() %></td>
        <td><%= item.getSubtotal() %></td>
    </tr>
    <% }} %>
</table>
<h3>总计: <%= total %> 元</h3>
<button>结算(Demo)</button>
</body>
</html>