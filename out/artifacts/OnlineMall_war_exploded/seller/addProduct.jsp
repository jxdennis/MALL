<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布新商品</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f8f9fa; padding: 20px; }
        .form-box { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; font-weight: bold; margin-bottom: 5px; }
        .form-group input, .form-group select, .form-group textarea { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { background-color: #007bff; color: white; border: none; padding: 12px 20px; font-size: 16px; border-radius: 4px; cursor: pointer; width: 100%; }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>
<div class="form-box">
    <h2>📦 上架新商品</h2>
    <form action="${pageContext.request.contextPath}/seller/addProduct" method="post" enctype="multipart/form-data">

        <div class="form-group">
            <label>商品名称：</label>
            <input type="text" name="name" required>
        </div>

        <div class="form-group">
            <label>商品分类：</label>
            <select name="category" required>
                <option value="数码电子">💻 数码电子</option>
                <option value="服装服饰">👕 服装服饰</option>
                <option value="食品生鲜">🍎 食品生鲜</option>
                <option value="家居日用">🛏️ 家居日用</option>
                <option value="图书文娱">📚 图书文娱</option>
            </select>
        </div>

        <div class="form-group">
            <label>商品描述：</label>
            <textarea name="description" rows="3"></textarea>
        </div>

        <div class="form-group">
            <label>原价（元）：</label>
            <input type="number" name="originalPrice" step="0.01" required>
        </div>

        <div class="form-group">
            <label>库存数量：</label>
            <input type="number" name="stock" required>
        </div>

        <div class="form-group">
            <label>商品图片：</label>
            <input type="file" name="image" accept="image/*" required>
        </div>

        <div class="form-group">
            <label>打折策略：</label>
            <select name="discountStrategyId">
                <option value="1">原价销售 (无折扣)</option>
                <option value="2">全场八折 (比例折扣)</option>
                <option value="3">满100减20 (满减折扣)</option>
            </select>
        </div>

        <button type="submit">确认发布</button>
    </form>
</div>
</body>
</html>