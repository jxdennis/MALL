package com.shop.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class OnlineSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        change(se, 1);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        change(se, -1);
    }

    private void change(HttpSessionEvent event, int delta) {
        Object value = event.getSession().getServletContext().getAttribute("onlineCount");
        int count = value instanceof Integer ? (Integer) value : 0;
        event.getSession().getServletContext().setAttribute("onlineCount", Math.max(0, count + delta));
    }
}
