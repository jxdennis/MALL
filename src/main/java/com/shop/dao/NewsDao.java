package com.shop.dao;

import com.shop.entity.News;
import com.shop.entity.NewsComment;
import com.shop.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.util.List;

public class NewsDao {
    private QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());

    // 1. 查询所有新闻（按最新发布排序）
    public List<News> findAll() throws Exception {
        String sql = "SELECT id, title, content, view_count AS viewCount, publish_time AS publishTime FROM news ORDER BY id DESC";
        return qr.query(sql, new BeanListHandler<>(News.class));
    }

    // 2. 查询排行榜前 5 名（按浏览量 view_count 倒序）
    public List<News> findTopRanking() throws Exception {
        String sql = "SELECT id, title, content, view_count AS viewCount, publish_time AS publishTime FROM news ORDER BY view_count DESC LIMIT 5";
        return qr.query(sql, new BeanListHandler<>(News.class));
    }

    // 3. 阅读新闻详情（🚨 核心：查询前，先让浏览量 +1）
    public News readNews(int id) throws Exception {
        // 浏览量累加
        qr.update("UPDATE news SET view_count = view_count + 1 WHERE id = ?", id);
        // 返回详细数据
        String sql = "SELECT id, title, content, view_count AS viewCount, publish_time AS publishTime FROM news WHERE id = ?";
        return qr.query(sql, new BeanHandler<>(News.class), id);
    }

    // 4. 查询某条新闻的所有评论
    public List<NewsComment> findCommentsByNewsId(int newsId) throws Exception {
        String sql = "SELECT id, news_id AS newsId, username, content, comment_time AS commentTime FROM news_comment WHERE news_id = ? ORDER BY id DESC";
        return qr.query(sql, new BeanListHandler<>(NewsComment.class), newsId);
    }

    // 5. 发布新闻
    public void addNews(String title, String content) throws Exception {
        qr.update("INSERT INTO news (title, content) VALUES (?, ?)", title, content);
    }

    // 6. 发表评论
    public void addComment(int newsId, String username, String content) throws Exception {
        qr.update("INSERT INTO news_comment (news_id, username, content) VALUES (?, ?, ?)", newsId, username, content);
    }
}