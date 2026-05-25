<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>多角色网上商城 - 欢迎您</title>
    <a href="${pageContext.request.contextPath}/article" style="background:#f1c40f; color:#333; font-weight:bold; padding: 10px 20px; border-radius: 30px; text-decoration: none;">📰 前往新闻大厅</a>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; background: #f4f7f6; color: #333; }
        .hero { background: linear-gradient(135deg, #0052D4, #4364F7, #6FB1FC); padding: 80px 20px; text-align: center; color: white; }
        .hero h1 { font-size: 48px; margin-bottom: 10px; }
        .hero p { font-size: 18px; margin-bottom: 30px; opacity: 0.9; }
        .btn-group a { display: inline-block; padding: 12px 30px; margin: 0 15px; border-radius: 30px; text-decoration: none; font-size: 18px; font-weight: bold; transition: all 0.3s; }
        .btn-login { background: white; color: #0052D4; }
        .btn-login:hover { background: #eee; transform: translateY(-3px); }
        .btn-register { border: 2px solid white; color: white; }
        .btn-register:hover { background: white; color: #0052D4; transform: translateY(-3px); }
        .news-section { max-width: 800px; margin: 40px auto; padding: 20px; background: white; border-radius: 10px; box-shadow: 0 5px 15px rgba(0,0,0,0.05); }
        .news-section h3 { border-bottom: 2px solid #4364F7; padding-bottom: 10px; margin-top: 0; color: #4364F7; }
        .news-list { list-style: none; padding: 0; }
        .news-item { padding: 15px 0; border-bottom: 1px dashed #eee; display: flex; flex-direction: column; }
        .news-item:last-child { border-bottom: none; }
        .news-tag { font-size: 12px; background: #ff4757; color: white; padding: 3px 8px; border-radius: 4px; display: inline-block; width: max-content; margin-bottom: 8px;}
        .news-title { font-size: 16px; font-weight: bold; }
        .news-desc { font-size: 14px; color: #666; margin-top: 5px; }
    </style>
</head>
<body>
<div class="hero">
    <h1>🌟 综合多角色网上商城 🌟</h1>
    <p>买家尽享实惠折扣，商家轻松管理商品。一站式购物体验尽在这里！</p>
    <div class="btn-group">
        <a href="${pageContext.request.contextPath}/login.jsp" class="btn-login">马上登录</a>
        <a href="${pageContext.request.contextPath}/register.jsp" class="btn-register">免费注册</a>
    </div>
</div>

<div class="news-section">
    <h3>📢 商户最新购物新闻 & 上新快报</h3>
    <ul class="news-list" id="newsBox">
        <li class="news-item">数据努力加载中...</li>
    </ul>
</div>

<script>
    window.onload = function() {
        var xhr = new XMLHttpRequest();
        // 🚨 修复点：绝对路径请求后台新闻接口
        xhr.open("GET", "${pageContext.request.contextPath}/news", true);
        xhr.onload = function() {
            if(xhr.status === 200) {
                var newsData = JSON.parse(xhr.responseText);
                var html = "";
                if(newsData.length === 0) {
                    html = "<li class='news-item'>暂无商家发布新闻，敬请期待！</li>";
                } else {
                    for(var i=0; i<newsData.length; i++) {
                        var news = newsData[i];
                        html += "<li class='news-item'>" +
                            "<span class='news-tag'>商户 " + news.merchant_name + " 发布</span>" +
                            "<div class='news-title'>🔥 重磅上新：" + news.name + "</div>" +
                            "<div class='news-desc'>" + news.description + "</div>" +
                            "</li>";
                    }
                }
                document.getElementById("newsBox").innerHTML = html;
            }
        };
        xhr.send();
    };
</script>
</body>
</html>