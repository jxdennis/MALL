<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录 - 多角色网上商城</title>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f6f9; display: flex; align-items: center; justify-content: center; height: 100vh; }
        .login-box { width: 360px; padding: 30px; background: white; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        h2 { text-align: center; color: #333; margin-bottom: 25px; }
        label { font-size: 14px; color: #555; font-weight: bold; }
        input[type="text"], input[type="password"] { width: 100%; padding: 10px; margin: 8px 0 20px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 14px; }
        button { width: 100%; padding: 12px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; font-weight: bold; }
        button:hover { background-color: #0056b3; }
        .error-msg { background-color: #f8d7da; color: #721c24; padding: 10px; border-radius: 4px; font-size: 14px; margin-bottom: 15px; text-align: center; border: 1px solid #f5c6cb; }
        .footer-links { margin-top: 20px; text-align: center; font-size: 14px; color: #666; }
        .footer-links a { color: #007bff; text-decoration: none; font-weight: bold; }
        .footer-links a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<div class="login-box">
    <h2>系统用户登录</h2>

    <%-- 读取并展示后端传递的登录错误提示 --%>
    <%
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
    %>
    <div class="error-msg"><%= msg %></div>
    <%
        }
    %>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label>用户名 / 账号:</label>
        <input type="text" name="username" required placeholder="请输入您的用户名">

        <label>密 码:</label>
        <input type="password" name="password" required placeholder="请输入您的密码">

        <button type="submit">登 录</button>
    </form>

    <div class="footer-links">
        还没有账号？ <a href="${pageContext.request.contextPath}/register.jsp">立即注册账号</a>
    </div>
</div>
</body>
</html>