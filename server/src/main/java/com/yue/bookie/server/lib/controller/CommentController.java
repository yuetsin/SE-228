package com.yue.bookie.server.lib.controller;

import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.Date;

@RestController
public class CommentController {
    @RequestMapping(value = "/comment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String comment(String content, String isbn) {
        if (content.length() > 1024) {
            content = content.substring(0, 1024);
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
            if (rs.first()) {
                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                String sql_get_uid = "SELECT * FROM s_user WHERE name = ?";
                PreparedStatement ps_getuid = conn.prepareStatement(sql_get_uid);
                ps_getuid.setString(1, userDetails.getUsername());
                rs = ps_getuid.executeQuery();
                if (rs.first()) {
                    Integer u_id = rs.getInt("id");

                    String sql_check_if_purchased = "SELECT * FROM bills WHERE user_id = ? AND isbn = ?";
                    PreparedStatement ps_check_ip = conn.prepareStatement(sql_check_if_purchased);
                    ps_check_ip.setInt(1, u_id);
                    ps_check_ip.setString(2, isbn);
                    rs = ps_check_ip.executeQuery();

                    Boolean purchased = false;
                    if (rs.first()) {
                        purchased = true;
                    }

                    java.util.Date date = new Date();
                    Timestamp timeStamp = new Timestamp(date.getTime());
                    String sql_bill = "INSERT INTO comments(comm_uuid, user_id, time, purchased, isbn, comment_content) VALUES (UUID(), ?, ?, ?, ?, ?)";
                    PreparedStatement ps_bill = conn.prepareStatement(sql_bill);

                    ps_bill.setInt(1, u_id);
                    ps_bill.setTimestamp(2, timeStamp);
                    ps_bill.setBoolean(3, purchased);
                    ps_bill.setString(4, isbn);
                    ps_bill.setString(5, content);
                    if (ps_bill.executeUpdate() == 1) {
                        return String.format("{\"status\": \"ok\"}");
                    } else {
                        return "{\"status\": \"internal_error\"}";
                    }
                } else {
                    return "{\"status\": \"internal_error\"}";
                }
            } else {
                return "{\"status\": \"no_book_found\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}
