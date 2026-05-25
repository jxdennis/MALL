package com.shop.controller;
import com.alibaba.fastjson.JSON;
import com.shop.dao.RegionDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/region")
public class RegionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int parentId = Integer.parseInt(req.getParameter("parentId"));
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(JSON.toJSONString(new RegionDao().findByParentId(parentId)));
        } catch (Exception e) { e.printStackTrace(); }
    }
}