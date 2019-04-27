package com.yue.bookie.server.lib.controller;


import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class MakeBillController {
    @RequestMapping(value = "/bill", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String bill(String isbn, Integer count) {
        if (count < 1) {
            return "{\"status\": \"bad_amount\"}";
        }
        try {
            SecurityConfig sC = new SecurityConfig();
            sC.initDataBase();
            Class.forName(BehaviorConfig.driverClass);
            Connection conn = DriverManager.getConnection(BehaviorConfig.dbUrl, sC.userName, sC.passWord);
            Statement stmt = conn.createStatement();
            stmt.executeQuery("USE bookie;");
            String sql = "SELECT * FROM bookie.book_library WHERE isbn = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}
