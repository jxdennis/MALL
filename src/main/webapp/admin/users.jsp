<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.User, java.util.List" %>
<html>
<head>
    <title>用户管理</title>
    <style>
        body { font-family: Arial, "Microsoft YaHei", sans-serif; background:#f5f7fb; padding:32px; }
        .wrap { max-width:1100px; margin:auto; background:white; padding:24px; border-radius:8px; box-shadow:0 8px 24px rgba(15,23,42,.08); }
        table { width:100%; border-collapse:collapse; }
        th, td { padding:12px; border-bottom:1px solid #e5e7eb; text-align:left; }
        a { color:white; background:#2563eb; border-radius:6px; padding:8px 12px; text-decoration:none; }
        .danger { background:#dc2626; }
    </style>
</head>
<body>
<div class="wrap">
    <h2>用户管理</h2>
    <p><a href="${pageContext.request.contextPath}/admin/dashboard">返回看板</a></p>
    <table>
        <tr><th>ID</th><th>用户名</th><th>角色</th><th>地区</th><th>注册时间</th><th>操作</th></tr>
        <% List<User> users = (List<User>) request.getAttribute("users");
           if (users != null) { for (User u : users) { %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getUsername() %></td>
            <td><%= u.getRole() %></td>
            <td><%= u.getProvinceName() %> / <%= u.getCityName() %> / <%= u.getDistrictName() %></td>
            <td><%= u.getRegTime() %></td>
            <td><a class="danger" onclick="return confirm('确定删除该用户？')" href="${pageContext.request.contextPath}/admin/deleteUser?id=<%= u.getId() %>">删除</a></td>
        </tr>
        <% }} %>
    </table>
</div>
</body>
</html>
