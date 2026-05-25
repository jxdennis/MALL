<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册 - 多角色网上商城</title>
    <style>
        body { margin: 0; font-family: Arial, "Microsoft YaHei", sans-serif; background: #f5f7fb; padding: 34px; }
        .box { max-width: 760px; margin: auto; background: white; border-radius: 8px; padding: 30px; box-shadow: 0 10px 30px rgba(15,23,42,.08); }
        .grid { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }
        label { display: block; margin-bottom: 6px; font-weight: 700; }
        input, select { width: 100%; box-sizing: border-box; padding: 10px; border: 1px solid #cbd5e1; border-radius: 6px; }
        .roles { display: flex; gap: 14px; margin-bottom: 16px; }
        .roles label { font-weight: 400; }
        button { margin-top: 20px; padding: 12px 22px; border: 0; border-radius: 6px; background: #0f766e; color: white; font-weight: 700; cursor: pointer; }
        .msg { padding: 10px; border-radius: 6px; background: #fee2e2; color: #991b1b; margin-bottom: 12px; }
        .hint { font-size: 13px; margin-top: 6px; min-height: 18px; }
        .ok { color: #15803d; }
        .bad { color: #b91c1c; }
        @media(max-width: 760px) { .grid { grid-template-columns: 1fr; } }
    </style>
    <script>
        function $(id) { return document.getElementById(id); }
        function checkUsername() {
            var username = $("username").value.trim();
            if (username.length < 3) {
                $("usernameHint").innerHTML = "<span class='bad'>用户名至少 3 个字符</span>";
                return;
            }
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "${pageContext.request.contextPath}/checkUser", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            xhr.onload = function() {
                $("usernameHint").innerHTML = xhr.responseText === "exists"
                    ? "<span class='bad'>用户名已被注册</span>"
                    : "<span class='ok'>用户名可用</span>";
            };
            xhr.send("username=" + encodeURIComponent(username));
        }
        function loadRegion(parentId, targetId) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "${pageContext.request.contextPath}/region?parentId=" + encodeURIComponent(parentId), true);
            xhr.onload = function() {
                var list = JSON.parse(xhr.responseText);
                var html = "<option value=''>请选择</option>";
                for (var i = 0; i < list.length; i++) {
                    html += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
                $(targetId).innerHTML = html;
                if (targetId === "city") $("district").innerHTML = "<option value=''>请选择</option>";
            };
            xhr.send();
        }
        function validateForm() {
            var pwd = $("password").value;
            var confirm = $("confirmPassword").value;
            var idCard = $("idCard").value;
            var pwdRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
            var idRegex = /^[1-9]\d{5}(18|19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}[\dXx]$/;
            if (!pwdRegex.test(pwd)) { alert("密码至少 8 位，并包含字母和数字"); return false; }
            if (pwd !== confirm) { alert("两次输入的密码不一致"); return false; }
            if (!idRegex.test(idCard)) { alert("身份证号格式不正确"); return false; }
            if (!$("province").value || !$("city").value || !$("district").value) { alert("请选择完整地区"); return false; }
            return true;
        }
        window.onload = function() { loadRegion(0, "province"); };
    </script>
</head>
<body>
<div class="box">
    <h2>创建商城账号</h2>
    <% if (request.getAttribute("msg") != null) { %><div class="msg"><%= request.getAttribute("msg") %></div><% } %>
    <form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return validateForm()">
        <div class="roles">
            <label><input type="radio" name="role" value="buyer" checked> 买家</label>
            <label><input type="radio" name="role" value="seller"> 商家</label>
            <label><input type="radio" name="role" value="admin"> 管理员</label>
        </div>
        <div class="grid">
            <div><label>用户名</label><input id="username" name="username" onblur="checkUsername()" required><div id="usernameHint" class="hint"></div></div>
            <div><label>身份证号</label><input id="idCard" name="idCard" required></div>
            <div><label>密码</label><input type="password" id="password" name="password" required></div>
            <div><label>确认密码</label><input type="password" id="confirmPassword" name="confirmPassword" required></div>
            <div><label>省</label><select id="province" name="province" onchange="loadRegion(this.value, 'city')" required></select></div>
            <div><label>市</label><select id="city" name="city" onchange="loadRegion(this.value, 'district')" required><option value="">请选择</option></select></div>
            <div><label>区</label><select id="district" name="district" required><option value="">请选择</option></select></div>
        </div>
        <button type="submit">注册</button>
        <a href="${pageContext.request.contextPath}/login.jsp" style="margin-left:14px;color:#2563eb;">已有账号，去登录</a>
    </form>
</div>
</body>
</html>
