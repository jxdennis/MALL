<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>下单成功</title></head>
<body style="font-family:Arial,'Microsoft YaHei',sans-serif;background:#f5f7fb;padding:40px;">
<div style="max-width:640px;margin:auto;background:white;padding:30px;border-radius:8px;">
    <h2>下单成功</h2>
    <p>订单号：<strong><%= request.getAttribute("orderNo") %></strong></p>
    <a href="${pageContext.request.contextPath}/buyer/products">返回商城</a>
</div>
</body>
</html>
