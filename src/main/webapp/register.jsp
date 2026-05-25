<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>注册</title>
    <script>
        function checkUsername() {
            var username = document.getElementById("username").value;
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "checkUser", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.onload = function() {
                var msg = document.getElementById("unameMsg");
                if(xhr.responseText === "exists") msg.innerHTML = "<font color='red'>用户名已存在</font>";
                else msg.innerHTML = "<font color='green'>可使用</font>";
            };
            xhr.send("username=" + username);
        }

        function loadRegion(parentId, targetId) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "region?parentId=" + parentId, true);
            xhr.onload = function() {
                var data = JSON.parse(xhr.responseText);
                var html = '<option value="">请选择</option>';
                for(var i=0; i<data.length; i++) html += '<option value="'+data[i].id+'">'+data[i].name+'</option>';
                document.getElementById(targetId).innerHTML = html;
            };
            xhr.send();
        }

        function validateForm() {
            var pwd = document.getElementById("pwd").value;
            var pwd2 = document.getElementById("pwd2").value;
            var idCard = document.getElementById("idCard").value;

            var pwdRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
            var idCardRegex = /^[1-9]\d{5}(18|19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}[\dX]$/i;

            if(!pwdRegex.test(pwd)) { alert("密码需至少8位且包含字母和数字"); return false; }
            if(pwd !== pwd2) { alert("两次密码不一致"); return false; }
            if(!idCardRegex.test(idCard)) { alert("身份证格式错误"); return false; }
            return true;
        }

        window.onload = function() { loadRegion(0, 'province'); };
    </script>
</head>
<body>
<h2>用户注册</h2>
<form action="register" method="post" onsubmit="return validateForm()">
    角色: <input type="radio" name="role" value="buyer" checked>买家
    <input type="radio" name="role" value="seller">卖家<br><br>
    用户名: <input type="text" id="username" name="username" onblur="checkUsername()"> <span id="unameMsg"></span><br><br>
    密码: <input type="password" id="pwd" name="password"><br><br>
    确认密码: <input type="password" id="pwd2"><br><br>
    身份证号: <input type="text" id="idCard" name="idCard"><br><br>
    地区:
    <select name="province" id="province" onchange="loadRegion(this.value, 'city')"><option>请选择</option></select> 省
    <select name="city" id="city" onchange="loadRegion(this.value, 'district')"><option>请选择</option></select> 市
    <select name="district" id="district"><option>请选择</option></select> 区<br><br>
    <button type="submit">注册</button>
</form>
</body>
</html>