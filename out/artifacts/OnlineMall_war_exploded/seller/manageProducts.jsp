<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.Product" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>商品管理 - 商家后台</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f8f9fa; margin: 0; padding: 20px; }
        .container { max-width: 1200px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        h2 { color: #2c3e50; border-bottom: 2px solid #3498db; padding-bottom: 10px; margin-top: 0; display: flex; justify-content: space-between; align-items: center; }
        .btn-back { font-size: 14px; background: #95a5a6; color: white; padding: 6px 12px; border-radius: 4px; text-decoration: none; }
        .btn-back:hover { background: #7f8c8d; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #e2e8f0; padding: 12px; text-align: center; }
        th { background-color: #f1f5f9; color: #475569; }
        .img-preview { width: 60px; height: 60px; object-fit: cover; border-radius: 4px; }
        .btn-delete { background-color: #e74c3c; color: white; border: none; padding: 6px 12px; border-radius: 4px; text-decoration: none; font-size: 14px; cursor: pointer; }
        .btn-delete:hover { background-color: #c0392b; }
        .empty-msg { text-align: center; color: #7f8c8d; padding: 40px; }
        .pagination { text-align: center; margin-top: 25px; font-size: 16px; }
        .page-btn { background: #3498db; color: white; padding: 8px 15px; border-radius: 4px; text-decoration: none; margin: 0 10px; }
        .page-btn:hover { background: #2980b9; }
    </style>
</head>
<body>
<div class="container">
    <h2>
        🛒 我发布的商品列表
        <a href="${pageContext.request.contextPath}/seller/index.jsp" class="btn-back">返回控制台</a>
    </h2>

    <%
        List<Product> list = (List<Product>) request.getAttribute("productList");
        Integer currentPage = (Integer) request.getAttribute("currentPage");
        if (currentPage == null) currentPage = 1;

        if (list == null || list.isEmpty()) {
    %>
    <div class="empty-msg">当前页面空空如也，或者您还没发布过商品哦！</div>
    <% } else { %>
    <table>
        <tr>
            <th>图片</th>
            <th>商品名称</th>
            <th>分类</th> <th>原价 (元)</th>
            <th>库存</th>
            <th>操作</th>
        </tr>
        <% for (Product p : list) { %>
        <tr>
            <td><img src="${pageContext.request.contextPath}/<%= p.getImagePath() %>" class="img-preview" onerror="this.style.display='none'"></td>
            <td><%= p.getName() %></td>
            <td><span style="background: #e1f5fe; color: #0288d1; padding: 3px 8px; border-radius: 12px; font-size: 13px;"><%= p.getCategory() %></span></td>
            <td><%= p.getOriginalPrice() %></td>
            <td><%= p.getStock() %></td>
            <td>
                <a href="${pageContext.request.contextPath}/seller/manage?action=delete&id=<%= p.getId() %>"
                   class="btn-delete" onclick="return confirm('确定要下架并删除该商品吗？此操作不可恢复！');">🗑️ 删除</a>
            </td>
        </tr>
        <% } %>
    </table>
    <% } %>

    <div class="pagination">
        <% if (currentPage > 1) { %>
        <a href="${pageContext.request.contextPath}/seller/manage?pageNo=<%= currentPage - 1 %>" class="page-btn">⬅️ 上一页</a>
        <% } else { %>
        <span class="page-btn" style="background: #ccc; cursor: not-allowed;">⬅️ 上一页</span>
        <% } %>

        <span style="margin: 0 15px; font-weight: bold; color: #34495e;">第 <%= currentPage %> 页</span>

        <% if (list != null && list.size() == 5) { %>
        <a href="${pageContext.request.contextPath}/seller/manage?pageNo=<%= currentPage + 1 %>" class="page-btn">下一页 ➡️</a>
        <% } else { %>
        <span class="page-btn" style="background: #ccc; cursor: not-allowed;">下一页 ➡️</span>
        <% } %>
    </div>
</div>
</body>
</html>