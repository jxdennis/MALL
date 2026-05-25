package com.shop.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

// 🚨 核心注解：有了它，Tomcat 启动时会自动扫到并执行这个管家
@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 这里是 Tomcat 刚刚启动时的瞬间
        System.out.println("=========================================");
        System.out.println("🚀 多角色网上商城系统 - 核心引擎启动中...");

        // 比如：你可以在这里把项目的绝对路径存进全局，方便到处用
        sce.getServletContext().setAttribute("basePath", sce.getServletContext().getContextPath());

        System.out.println("✅ 系统初始化完成，随时准备迎接买家！");
        System.out.println("=========================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 这里是 Tomcat 关闭时的瞬间（可以用来释放数据库连接池等清理工作）
        System.out.println("🛑 商城系统正在安全关闭...");
    }
}