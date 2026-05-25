<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>多角色网上商城</title>
    <style>
        body { margin: 0; font-family: Arial, "Microsoft YaHei", sans-serif; background: #f5f7fb; color: #1f2937; }
        .hero { min-height: 76vh; padding: 56px 8vw; background: linear-gradient(135deg, #0f766e, #2563eb); color: white; display: flex; flex-direction: column; justify-content: center; }
        h1 { font-size: 48px; margin: 0 0 16px; }
        p { font-size: 18px; max-width: 720px; line-height: 1.8; }
        .actions { margin-top: 28px; display: flex; gap: 16px; }
        .btn { color: #0f172a; background: white; padding: 13px 24px; border-radius: 6px; text-decoration: none; font-weight: 700; }
        .btn.secondary { color: white; background: rgba(255,255,255,.18); border: 1px solid rgba(255,255,255,.7); }
        .features { display: grid; grid-template-columns: repeat(3, 1fr); gap: 18px; padding: 28px 8vw 40px; }
        .feature { background: white; border-radius: 8px; padding: 22px; box-shadow: 0 8px 24px rgba(15,23,42,.06); }
        @media(max-width: 800px) { h1 { font-size: 34px; } .features { grid-template-columns: 1fr; } .actions { flex-direction: column; } }
    </style>
</head>
<body>
<section class="hero">
    <h1>多角色网上商城</h1>
    <p>买家浏览商品并下单，商家维护商品与折扣策略，管理员查看用户和商品信息。系统基于 Servlet + JSP + MySQL + C3P0 + DbUtils，按 MVC 分层实现。</p>
    <div class="actions">
        <a class="btn" href="${pageContext.request.contextPath}/login.jsp">登录系统</a>
        <a class="btn secondary" href="${pageContext.request.contextPath}/register.jsp">创建账号</a>
    </div>
</section>
<section class="features">
    <div class="feature"><h3>买家</h3><p>浏览商品、加入购物车、查看折后价格并提交订单。</p></div>
    <div class="feature"><h3>商家</h3><p>上传商品图片，维护库存，为商品选择折扣策略。</p></div>
    <div class="feature"><h3>管理员</h3><p>管理所有用户和商品，查看商城基础数据。</p></div>
</section>
</body>
</html>
