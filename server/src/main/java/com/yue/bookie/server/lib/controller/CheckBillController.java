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
public class CheckBillController {
    @RequestMapping(value = "/bill", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String bill() {
        try {
            SecurityConfig sC = new SecurityConfig();
            sC.initDataBase();
            Class.forName(BehaviorConfig.driverClass);
            Connection conn = DriverManager.getConnection(BehaviorConfig.dbUrl, sC.userName, sC.passWord);
            Statement stmt = conn.createStatement();
            stmt.executeQuery("USE bookie;");

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String sql_get_uid = "SELECT * FROM s_user WHERE name = ?";
            PreparedStatement ps_getuid = conn.prepareStatement(sql_get_uid);
            ps_getuid.setString(1, userDetails.getUsername());
            ResultSet rs = ps_getuid.executeQuery();
            if (rs.first()) {
                Integer u_id = rs.getInt("id");
                String bill_check_id = "SELECT bills.isbn, title, author, count, time, bill_uuid FROM bills, book_library WHERE user_id = ? AND bills.isbn = book_library.isbn AND later = FALSE";
                PreparedStatement ps = conn.prepareStatement(bill_check_id);
                ps.setInt(1, u_id);
                rs = ps.executeQuery();
                return JSONPacker.resultSetToJson(rs);
            } else {
                return "{\"status\": \"no_user_found\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}