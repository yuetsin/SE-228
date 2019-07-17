package com.yue.bookie.server.lib.controller;


import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import com.yue.bookie.server.lib.packer.JSONPacker;
import com.yue.bookie.server.lib.service.BookieUtils;
import com.yue.bookie.server.lib.struct.Comment;
import com.yue.bookie.server.lib.struct.Order;
import org.json.JSONArray;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.List;

@RestController
public class CheckBillController {
    @RequestMapping(value = "/bill", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String bill() {
        try {
            List<Order> books = BookieUtils.service.getOrders();
            JSONArray JSONArray = new JSONArray(books);
            System.out.println(JSONArray.toString());
            return String.format("{\"status\": \"ok\", \"data\": %s}", JSONArray.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}