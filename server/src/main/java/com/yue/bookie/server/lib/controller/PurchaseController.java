package com.yue.bookie.server.lib.controller;


import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.*;

@RestController
public class PurchaseController {
    @RequestMapping(value = "/buy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String buy(String isbn, Integer count, Boolean later) {
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
            if (rs.first()) {
                Integer storage = rs.getInt("storage");
                BigDecimal price = rs.getBigDecimal("coupon_price").multiply(new BigDecimal(count.toString()));
                if (storage >= count) {
                    String sql_insert = "UPDATE book_library SET storage = ? WHERE isbn = ?";
                    PreparedStatement ps_insert = conn.prepareStatement(sql_insert);
                    ps_insert.setInt(1, storage - count);
                    ps_insert.setString(2, isbn);

                    if (ps_insert.executeUpdate() == 1) {

                        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        String sql_get_uid = "SELECT * FROM s_user WHERE name = ?";
                        PreparedStatement ps_getuid = conn.prepareStatement(sql_get_uid);
                        ps_getuid.setString(1, userDetails.getUsername());
                        rs = ps_getuid.executeQuery();
                        if (rs.first()) {
                            Integer u_id = rs.getInt("id");
                            Date date = new Date();
                            Timestamp timeStamp = new Timestamp(date.getTime());
                            String sql_bill = "INSERT INTO bills(bill_uuid, user_id, count, time, isbn, later) VALUES (UUID(), ?, ?, ?, ?, ?)";
                            PreparedStatement ps_bill = conn.prepareStatement(sql_bill);
                            ps_bill.setInt(1, u_id);
                            ps_bill.setInt(2, count);
                            ps_bill.setTimestamp(3, timeStamp);
                            ps_bill.setString(4, isbn);
                            ps_bill.setBoolean(5, later);
                            if (ps_bill.executeUpdate() == 1) {
                                if (later) {
                                    return "{\"status\": \"ok\"}";
                                } else {
                                    return String.format("{\"status\": \"ok\", \"cost\": \"%s\"}", price.toString());
                                }
                            } else {
                                return "{\"status\": \"internal_error\"}";
                            }
                        } else {
                            return "{\"status\": \"internal_error\"}";
                        }
                    } else {
                        return "{\"status\": \"internal_error\"}";
                    }
                } else {
                    return "{\"status\": \"no_enough_storage\"}";
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