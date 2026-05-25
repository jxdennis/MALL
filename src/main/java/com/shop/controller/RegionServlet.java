package com.shop.controller;

import com.shop.entity.Region;
import com.shop.service.RegionService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/region")
public class RegionServlet extends HttpServlet {
    private final RegionService regionService = new RegionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int parentId = Integer.parseInt(req.getParameter("parentId"));
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write(toJson(regionService.findChildren(parentId)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String toJson(List<Region> regions) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < regions.size(); i++) {
            Region region = regions.get(i);
            if (i > 0) {
                json.append(',');
            }
            json.append("{\"id\":").append(region.getId())
                    .append(",\"name\":\"").append(escape(region.getName()))
                    .append("\",\"parentId\":").append(region.getParentId()).append('}');
        }
        return json.append(']').toString();
    }

    private String escape(String value) {
        return value == null ? "" : value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
