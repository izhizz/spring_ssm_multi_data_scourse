package com.demo.utils;

public class DataSourceUtils {

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static String getDbKey() {
        return local.get();
    }

    public static void setDbKey(String dbKey) {
        local.set(dbKey);
    }

    public static void clear() {
        local.remove();
    }
}