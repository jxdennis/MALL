<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.News, com.shop.entity.NewsComment, java.util.List" %>
<html>
<head>
    <title>资讯详情</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f6f9; margin: 0; padding: 20px; }
        .article-box { max-width: 800px; margin: 0 auto; background: white; padding: 40px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); }
        h1 { text-align: center; color: #333; }
        .meta { text-align: center; color: #999; font-size: 14px; border-bottom: 1px solid #eee; padding-bottom: 20px; margin-bottom: 20px; }
        .meta span { color: #e74c3c; font-weight: bold; }
        .content { font-size: 16px; line-height: 1.8; color: #444; min-height: 150px; }

        .comment-section { max-width: 800px; margin: 20px auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); }
        .comment-form textarea { width: 100%; padding: 15px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; font-size: 15px; }
        .comment-form button { background: #2ecc71; color: white; border: none; padding: 10px 25px; border-radius: 4px; font-size: 16px; cursor: pointer; float: right; margin-top: 10px; }
        .comment-list { margin-top: 50px; }
        .comment-item { border-bottom: 1px dashed #eee; padding: 15px 0; }
        .c-user { font-weight: bold; color: #2980b9; font-size: 14px; }
        .c-time { color: #999; font-size: 12px; margin-left: 10px; }
        .c-content { margin-top: 8px; font-size: 15px; color: #333; }
    </style>
</head>
<body>
<% News news = (News) request.getAttribute("news"); %>
<div class="article-box">
    <a href="${pageContext.request.contextPath}/article" style="text-decoration:none; color:#0052D4;">⬅️ 返回新闻大厅</a>
    <h1><%= news.getTitle() %></h1>
    <div class="meta">
        发布时间：<%= news.getPublishTime() %>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
        历史浏览量：<span><%= news.getViewCount() %></span> 次
    </div>
    <div class="content"><%= news.getContent() %></div>
</div>

<div class="comment-section">
    <h3>💬 参与评论</h3>
    <form class="comment-form" action="${pageContext.request.contextPath}/article?action=comment" method="post">
        <input type="hidden" name="newsId" value="<%= news.getId() %>">
        <textarea name="content" rows="3" placeholder="说点什么吧..." required></textarea>
        <button type="submit">发表评论</button>
        <div style="clear:both;"></div>
    </form>

    <div class="comment-list">
        <h4>最新评论：</h4>
        <% List<NewsComment> comments = (List<NewsComment>) request.getAttribute("comments");
            if(comments == null || comments.isEmpty()) { %>
        <p style="color:#999;">暂无评论，快来抢沙发！</p>
        <% } else { for(NewsComment c : comments) { %>
        <div class="comment-item">
            <span class="c-user">👤 <%= c.getUsername() %></span>
            <span class="c-time"><%= c.getCommentTime() %></span>
            <div class="c-content"><%= c.getContent() %></div>
        </div>
        <% } } %>
    </div>
</div>
</body>
</html>