package com.yue.bookie.server.lib.controller;

import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import com.yue.bookie.server.lib.packer.JSONPacker;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
public class ISBNQueryController {
    @RequestMapping(value = "/isbn", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @CrossOrigin
    @ResponseBody
    public String isbn(String isbn) {
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
            return JSONPacker.resultSetToJson(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}