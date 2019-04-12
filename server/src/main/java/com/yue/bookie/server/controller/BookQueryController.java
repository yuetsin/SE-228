package com.yue.bookie.server.controller;

import com.yue.bookie.server.config.BehaviorConfig;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class BookQueryController {
    @RequestMapping(value = "/api", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String api() {
        if (!BehaviorConfig.useLegacyJson) {
            try {
                Class.forName(BehaviorConfig.driverClass);
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", BehaviorConfig.dbUserName, BehaviorConfig.dbUserPassword);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT user_name, age FROM imooc_goddess");
                while(rs.next()){
                    System.out.println(rs.getString("user_name")+" 年龄："+rs.getInt("age"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return "{}";
            }
        } else {
            // Keep legacy solution in case if you need it...
            File file02 = new File("assets/books/books.json");
            FileInputStream is;
            StringBuilder stringBuilder = null;
            try {
                if (file02.length() != 0) {
                    /**
                     * 文件有内容才去读文件
                     */
                    is = new FileInputStream(file02);
                    InputStreamReader streamReader = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(streamReader);
                    String line;
                    stringBuilder = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        // stringBuilder.append(line);
                        stringBuilder.append(line);
                    }
                    reader.close();
                    is.close();
                } else {
                    return "{}";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return String.valueOf(stringBuilder);
        }
    }
}
