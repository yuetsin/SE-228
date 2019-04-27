package com.yue.bookie.server.lib.controller;

import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import com.yue.bookie.server.lib.packer.JSONPacker;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;

@RestController
public class BookQueryController {
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String api(String q) {
        System.out.println("Gotta request " + q);
        if (!BehaviorConfig.useLegacyJson) {
            try {
                SecurityConfig sC = new SecurityConfig();
                sC.initDataBase();
                Class.forName(BehaviorConfig.driverClass);
                Connection conn = DriverManager.getConnection(BehaviorConfig.dbUrl, sC.userName, sC.passWord);
                Statement stmt = conn.createStatement();
                stmt.executeQuery("USE bookie;");
                String sql = "SELECT * FROM bookie.book_library WHERE locate(?, title) > 0 OR locate(?, author) > 0";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, q);
                ps.setString(2, q);
                ResultSet rs = ps.executeQuery();
                return JSONPacker.resultSetToJson(rs);
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
