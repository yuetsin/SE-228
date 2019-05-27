package com.yue.bookie.server.lib.controller;


import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import com.yue.bookie.server.lib.service.BookieUtils;
import com.yue.bookie.server.lib.struct.Book;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.*;

@RestController
public class PurchaseController {
    @RequestMapping(value = "/buy", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String buy(String isbn, Integer count, Boolean later, String receiver, String phoneNo, String address) {
        if (count < 1) {
            return "{\"status\": \"bad_amount\"}";
        }

        if (later) {
            // 加入购物车操作www
            try {
                BookieUtils.service.addToCart(isbn, count);
                return "{\"status\": \"ok\"}";
            } catch (Exception ex) {
                ex.printStackTrace();
                return "{\"status\": \"internal_error\"}";
            }
        } else {
            try {
                if (BookieUtils.service.buyFromCart(isbn, count, receiver, phoneNo, address)) {
                    return "{\"status\": \"ok\"}";
                } else {
                    return "{\"status\": \"storage_inadequate\"}";
                }
            } catch (Exception ex){
                ex.printStackTrace();
                return "{\"status\": \"internal_error\"}";
            }
        }
    }
}