package com.shop.util;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;

public class C3p0Utils {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    public static DataSource getDataSource() { return dataSource; }
    public static void destroy() { dataSource.close(); }
}