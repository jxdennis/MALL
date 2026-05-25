package com.shop.entity;
import java.util.Date;

public class NewsComment {
    private int id;
    private int newsId;
    private String username;
    private String content;
    private Date commentTime;

    // Getter 和 Setter (为了节约篇幅，请自行使用 IDEA 的 Alt+Insert 快捷键生成所有 Getter/Setter)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getNewsId() { return newsId; }
    public void setNewsId(int newsId) { this.newsId = newsId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Date getCommentTime() { return commentTime; }
    public void setCommentTime(Date commentTime) { this.commentTime = commentTime; }
}