package com.yue.bookie.server.config;

public class BehaviorConfig {
    public static Boolean useLegacyJson = false;
    public static final String deprecatedDriverClass = "com.mysql.jdbc.Driver";
    public static final String driverClass = "com.mysql.cj.jdbc.Driver";
    public static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/";
}
