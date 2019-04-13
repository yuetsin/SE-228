package com.yue.bookie.server.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SecurityConfig {
    private static String FILE_PATH_NAME = "jdbc.properties";
    public String userName;
    public String passWord;
    public void initDataBase() {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(FILE_PATH_NAME);
            Properties props = new Properties();
            props.load(in);
            in.close();
            userName = props.getProperty("jdbc.userName");
            passWord = props.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
