package com.shop.listener;

import com.shop.util.C3p0Utils;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        C3p0Utils.init();
        sce.getServletContext().setAttribute("basePath", sce.getServletContext().getContextPath());
        sce.getServletContext().setAttribute("onlineCount", 0);
        System.out.println("OnlineMall started. C3P0 datasource is ready.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        C3p0Utils.destroy();
        System.out.println("OnlineMall stopped. C3P0 datasource closed.");
    }
}
