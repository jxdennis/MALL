<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员看板</title>
    <style>
        body { margin: 0; font-family: Arial, "Microsoft YaHei", sans-serif; background: #f5f7fb; padding: 32px; }
        .wrap { max-width: 1000px; margin: auto; }
        .nav { display: flex; justify-content: space-between; align-items: center; margin-bottom: 22px; }
        .grid { display: grid; grid-template-columns: repeat(4, 1fr); gap: 16px; }
        .stat { background: white; border-radius: 8px; padding: 22px; box-shadow: 0 8px 24px rgba(15,23,42,.08); }
        .num { font-size: 32px; font-weight: 800; color: #2563eb; }
        a { color: white; background: #2563eb; border-radius: 6px; padding: 9px 14px; text-decoration: none; }
        @media(max-width: 760px) { .grid { grid-template-columns: 1fr 1fr; } }
    </style>
</head>
<body>
<div class="wrap">
    <div class="nav">
        <h2>管理员看板</h2>
        <div>
            <a href="${pageContext.request.contextPath}/admin/users">用户管理</a>
            <a href="${pageContext.request.contextPath}/admin/products">商品管理</a>
            <a href="${pageContext.request.contextPath}/logout" style="background:#dc2626;">退出</a>
        </div>
    </div>
    <div class="grid">
        <div class="stat"><div>买家</div><div class="num"><%= request.getAttribute("buyerCount") %></div></div>
        <div class="stat"><div>商家</div><div class="num"><%= request.getAttribute("sellerCount") %></div></div>
        <div class="stat"><div>管理员</div><div class="num"><%= request.getAttribute("adminCount") %></div></div>
        <div class="stat"><div>商品</div><div class="num"><%= request.getAttribute("productCount") %></div></div>
    </div>
</div>
</body>
</html>
