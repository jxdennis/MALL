<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.News, java.util.List" %>
<html>
<head>
    <title>资讯中心 - 新闻与排行</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f6f9; margin: 0; padding: 20px; }
        .header { background: #0052D4; color: white; padding: 20px; border-radius: 8px; margin-bottom: 20px; display: flex; justify-content: space-between;}
        .header a { color: white; text-decoration: none; border: 1px solid white; padding: 5px 15px; border-radius: 20px; }
        .main-container { display: flex; gap: 20px; max-width: 1200px; margin: 0 auto; }

        .news-list { flex: 7; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.05); }
        .news-item { border-bottom: 1px dashed #eee; padding: 15px 0; }
        .news-item a { font-size: 18px; color: #333; text-decoration: none; font-weight: bold; }
        .news-item a:hover { color: #0052D4; }
        .meta { font-size: 13px; color: #999; margin-top: 8px; }

        .ranking { flex: 3; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.05); height: max-content; }
        .ranking h3 { margin-top: 0; color: #e74c3c; border-bottom: 2px solid #e74c3c; padding-bottom: 10px; }
        .rank-item { padding: 10px 0; display: flex; align-items: center; justify-content: space-between;}
        .rank-num { background: #f1c40f; color: white; width: 22px; height: 22px; text-align: center; border-radius: 50%; font-size: 14px; line-height: 22px; margin-right: 10px; }
        .rank-title { flex: 1; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }
        .rank-views { color: #e74c3c; font-size: 13px; font-weight: bold; }
    </style>
</head>
<body>
<div class="header" style="max-width: 1160px; margin: 0 auto 20px;">
    <h2>📰 商城资讯中心</h2>
    <a href="${pageContext.request.contextPath}/index.jsp">🏠 返回首页</a>
</div>

<div class="main-container">
    <div class="news-list">
        <h3 style="margin-top:0;">最新发布</h3>
        <% List<News> list = (List<News>) request.getAttribute("newsList");
            if(list != null) { for(News n : list) { %>
        <div class="news-item">
            <a href="${pageContext.request.contextPath}/article?action=detail&id=<%= n.getId() %>"><%= n.getTitle() %></a>
            <div class="meta">发布时间：<%= n.getPublishTime() %> | 👁️ 浏览次数：<%= n.getViewCount() %></div>
        </div>
        <% } } %>
    </div>

    <div class="ranking">
        <h3>🔥 点击率 Top 5 排行榜</h3>
        <% List<News> top = (List<News>) request.getAttribute("topRanking");
            if(top != null) { int i = 1; for(News n : top) { %>
        <div class="rank-item">
            <span class="rank-num"><%= i++ %></span>
            <a href="${pageContext.request.contextPath}/article?action=detail&id=<%= n.getId() %>" class="rank-title" style="text-decoration:none; color:#333;"><%= n.getTitle() %></a>
            <span class="rank-views"><%= n.getViewCount() %> 阅</span>
        </div>
        <% } } %>
    </div>
</div>
</body>
</html>