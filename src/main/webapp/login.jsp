<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录 - 多角色网上商城</title>
    <style>
        body { margin: 0; font-family: Arial, "Microsoft YaHei", sans-serif; background: #eef2f7; min-height: 100vh; display: grid; place-items: center; }
        .box { width: 380px; background: white; border-radius: 8px; padding: 30px; box-shadow: 0 12px 32px rgba(15,23,42,.12); }
        h2 { margin-top: 0; }
        label { display: block; margin: 14px 0 6px; font-weight: 700; }
        input { width: 100%; box-sizing: border-box; padding: 11px; border: 1px solid #cbd5e1; border-radius: 6px; }
        button { width: 100%; margin-top: 22px; padding: 12px; border: 0; border-radius: 6px; background: #2563eb; color: white; font-weight: 700; cursor: pointer; }
        .msg { padding: 10px; border-radius: 6px; background: #fee2e2; color: #991b1b; margin-bottom: 12px; }
        .ok { background: #dcfce7; color: #166534; }
        .links { text-align: center; margin-top: 18px; }
        a { color: #2563eb; text-decoration: none; }
    </style>
</head>
<body>
<div class="box">
    <h2>账号登录</h2>
    <% if (request.getAttribute("msg") != null) { %>
        <div class="msg"><%= request.getAttribute("msg") %></div>
    <% } %>
    <% if ("1".equals(request.getParameter("registered"))) { %>
        <div class="msg ok">注册成功，请登录。</div>
    <% } %>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label>用户名</label>
        <input name="username" required autocomplete="username">
        <label>密码</label>
        <input type="password" name="password" required autocomplete="current-password">
        <button type="submit">登录</button>
    </form>
    <div class="links">
        <a href="${pageContext.request.contextPath}/register.jsp">注册账号</a>
        &nbsp;|&nbsp;
        <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a>
    </div>
</div>
</body>
</html>
