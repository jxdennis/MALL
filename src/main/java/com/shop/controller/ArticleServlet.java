package com.shop.controller;

import com.shop.dao.NewsDao;
import com.shop.entity.News;
import com.shop.entity.NewsComment;
import com.shop.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        NewsDao newsDao = new NewsDao();

        try {
            if ("detail".equals(action)) {
                // 动作：查看新闻详情
                int id = Integer.parseInt(req.getParameter("id"));
                News news = newsDao.readNews(id); // 此方法内部已实现了浏览量 +1
                List<NewsComment> comments = newsDao.findCommentsByNewsId(id);

                req.setAttribute("news", news);
                req.setAttribute("comments", comments);
                req.getRequestDispatcher("/newsDetail.jsp").forward(req, resp);

            } else if ("comment".equals(action)) {
                // 动作：发表评论
                int newsId = Integer.parseInt(req.getParameter("newsId"));
                String content = req.getParameter("content");
                User user = (User) req.getSession().getAttribute("loginUser");
                String username = (user != null) ? user.getUsername() : "匿名网友";

                newsDao.addComment(newsId, username, content);
                resp.sendRedirect(req.getContextPath() + "/article?action=detail&id=" + newsId);

            } else {
                // 默认动作：加载新闻大厅（左侧最新列表，右侧排行榜）
                List<News> newsList = newsDao.findAll();
                List<News> topRanking = newsDao.findTopRanking();

                req.setAttribute("newsList", newsList);
                req.setAttribute("topRanking", topRanking);
                req.getRequestDispatcher("/newsCenter.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}