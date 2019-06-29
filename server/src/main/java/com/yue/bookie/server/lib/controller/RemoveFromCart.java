package com.yue.bookie.server.lib.controller;


import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import com.yue.bookie.server.lib.service.BookieUtils;
import com.yue.bookie.server.lib.struct.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;

@RestController
public class RemoveFromCart {
    @RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String alter(String isbn) {
        try {
            BookieUtils.service.deleteFromCart(isbn);
            return "{\"status\": \"ok\"}";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}