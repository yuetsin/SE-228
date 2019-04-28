package com.yue.bookie.server.lib.controller;

import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;

@RestController
public class BuyFromMarketController {
    @RequestMapping(value = "/alter", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String alter(String uuid) {
        try {
            SecurityConfig sC = new SecurityConfig();
            sC.initDataBase();
            Class.forName(BehaviorConfig.driverClass);
            Connection conn = DriverManager.getConnection(BehaviorConfig.dbUrl, sC.userName, sC.passWord);
            Statement stmt = conn.createStatement();
            stmt.executeQuery("USE bookie;");

            String sql = "SELECT * FROM bookie.bills WHERE bill_uuid = ? AND later = TRUE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                String book_isbn = rs.getString("isbn");
                Integer amount = rs.getInt("count");
                sql = "SELECT * FROM bookie.book_library WHERE isbn = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, book_isbn);
                rs = ps.executeQuery();
                if (rs.first()) {
                    BigDecimal price = rs.getBigDecimal("coupon_price").multiply(new BigDecimal(amount.toString()));
                    Integer storage = rs.getInt("storage");
                    if (storage < amount) {
                        return "{\"status\": \"storage_not_enough\"}";
                    }
                    sql = "UPDATE bookie.book_library SET storage = ? WHERE isbn = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, storage - amount);
                    ps.setString(2, book_isbn);
                    if (ps.executeUpdate() != 1) {
                        return "{\"status\": \"internal_error\"}";
                    }
                    sql = "UPDATE bookie.bills SET time = ?, later = FALSE WHERE bill_uuid = ?";
                    ps = conn.prepareStatement(sql);
                    java.util.Date date = new Date();
                    Timestamp timeStamp = new Timestamp(date.getTime());
                    ps.setTimestamp(1, timeStamp);
                    ps.setString(2, uuid);
                    if (ps.executeUpdate() != 1) {
                        return "{\"status\": \"internal_error\"}";
                    }
                    return String.format("{\"status\": \"ok\", \"cost\": \"%s\"}", price.toString());
                } else {
                    return "{\"status\": \"book_not_found\"}";
                }
            } else {
                return "{\"status\": \"bad_uuid\"}";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}