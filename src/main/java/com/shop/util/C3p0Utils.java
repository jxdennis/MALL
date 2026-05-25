package com.shop.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;

public class C3p0Utils {
    private static final ComboPooledDataSource DATA_SOURCE = new ComboPooledDataSource();

    private C3p0Utils() {}

    public static DataSource getDataSource() {
        return DATA_SOURCE;
    }

    public static void init() {
        DATA_SOURCE.getJdbcUrl();
    }

    public static void destroy() {
        DATA_SOURCE.close();
    }
}
