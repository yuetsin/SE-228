package com.yue.bookie.server.lib.controller;


import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;

import com.yue.bookie.server.lib.utils.ValidationChecker;
import org.springframework.web.bind.annotation.*;

import java.sql.*;


@RestController
public class UserRegisterController {
    @RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String reg(String username, String password) {
        System.out.println("username = " + username + " password = " + password);
        if (!(ValidationChecker.isLegalPassword(password) && ValidationChecker.isLegalUserName(username))) {
            return "{\"status\": \"bad_format\"}";
        }
        try {
            SecurityConfig sC = new SecurityConfig();
            sC.initDataBase();
            Class.forName(BehaviorConfig.driverClass);
            Connection conn = DriverManager.getConnection(BehaviorConfig.dbUrl, sC.userName, sC.passWord);
            Statement stmt = conn.createStatement();
            stmt.executeQuery("USE bookie;");
            String sql = "SELECT * FROM bookie.s_user WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                return "{\"status\": \"user_already_exist\"}";
            }
            String sql_insert = "INSERT INTO s_user(username, password) VALUES (?, ?)";
            PreparedStatement ps_insert = conn.prepareStatement(sql_insert);
            ps_insert.setString(1, username);
            ps_insert.setString(2, password);

            if (ps_insert.executeUpdate() == 1) {
                return "{\"status\": \"ok\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "{\"status\": \"failed\"}";
    }
}