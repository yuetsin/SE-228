package com.yue.bookie.server.lib.controller;

import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import com.yue.bookie.server.lib.packer.JSONPacker;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class CommentListController {
    @RequestMapping(value = "/comlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String comlist(String isbn) {
        try {
            SecurityConfig sC = new SecurityConfig();
            sC.initDataBase();
            Class.forName(BehaviorConfig.driverClass);
            Connection conn = DriverManager.getConnection(BehaviorConfig.dbUrl, sC.userName, sC.passWord);
            Statement stmt = conn.createStatement();
            stmt.executeQuery("USE bookie;");

            String sql = "SELECT * FROM bookie.comments WHERE isbn = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            return JSONPacker.resultSetToJson(rs);

        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}
