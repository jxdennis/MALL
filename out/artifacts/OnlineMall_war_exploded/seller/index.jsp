<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.User" %>
<html>
<head>
    <title>商家后台管理中心 - 多角色商城</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f4f6f9; color: #333; }
        .header { background-color: #2c3e50; color: white; padding: 20px 40px; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .header h2 { margin: 0; font-size: 24px; font-weight: 600; }
        .user-info { font-size: 16px; }
        .user-info strong { color: #f1c40f; }
        .container { max-width: 1000px; margin: 40px auto; padding: 0 20px; }
        .dashboard-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 20px; }
        .card { background: white; padding: 30px; border-radius: 8px; text-align: center; box-shadow: 0 4px 15px rgba(0,0,0,0.05); transition: transform 0.2s; border-top: 4px solid #3498db; }
        .card:hover { transform: translateY(-5px); box-shadow: 0 6px 20px rgba(0,0,0,0.1); }
        .card h3 { margin-top: 0; color: #2c3e50; font-size: 22px; }
        .card p { color: #7f8c8d; margin-bottom: 25px; line-height: 1.5; }
        .btn { display: inline-block; padding: 12px 25px; background-color: #3498db; color: white; text-decoration: none; border-radius: 30px; font-weight: bold; transition: background 0.3s; }
        .btn:hover { background-color: #2980b9; }
        .btn-green { background-color: #2ecc71; border-top-color: #2ecc71; }
        .btn-green:hover { background-color: #27ae60; }
        .btn-red { background-color: #e74c3c; border-top-color: #e74c3c; }
        .btn-red:hover { background-color: #c0392b; }
    </style>
</head>
<body>
<%
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null || !"seller".equals(loginUser.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<a href="${pageContext.request.contextPath}/index.jsp" style="color: white; text-decoration: none; margin-left: 15px; border: 1px solid white; padding: 5px 10px; border-radius: 4px;">🏠 网站首页</a>
<div class="header">
    <h2>🏪 商家后台控制台</h2>
    <div class="user-info">欢迎回来，老板：<strong><%= loginUser.getUsername() %></strong></div>
</div>
<div class="container">
    <div class="dashboard-grid">
        <div class="card" style="border-top-color: #3498db;">
            <h3>📦 发布新商品</h3>
            <p>为您的店铺上架更多新宝贝，支持设置各种折扣策略来吸引买家。</p>
            <a href="${pageContext.request.contextPath}/seller/addProduct.jsp" class="btn">➕ 立即发布商品</a>
        </div>
        <div class="card" style="border-top-color: #2ecc71;">
            <h3>📋 管理商品</h3>
            <p>查看您已上架的所有商品库存，支持随时将售罄或需要调整的商品下架删除。</p>
            <a href="${pageContext.request.contextPath}/seller/manage" class="btn btn-green">🛠️ 管理我的商品</a>
        </div>
        <div class="card" style="border-top-color: #e74c3c;">
            <h3>🔒 账号安全</h3>
            <p>结束一天的工作？为了保护您的店铺数据安全，请记得退出登录。</p>
            <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-red">🚪 安全退出系统</a>
        </div>
    </div>
</div>
</body>
</html>