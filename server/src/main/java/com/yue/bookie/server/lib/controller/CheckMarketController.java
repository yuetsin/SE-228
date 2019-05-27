package com.yue.bookie.server.lib.controller;

import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import com.yue.bookie.server.lib.packer.JSONPacker;
import com.yue.bookie.server.lib.service.BookieUtils;
import com.yue.bookie.server.lib.struct.Book;
import com.yue.bookie.server.lib.struct.Cart;
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
public class CheckMarketController {
    @RequestMapping(value = "/market", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String market() {
        try {
            List<Cart> currentUserCart = BookieUtils.service.getUserCart();
            JSONArray JSONArray = new JSONArray(currentUserCart);
            System.out.println(JSONArray.toString());
            return String.format("{\"status\": \"ok\", \"data\": %s}", JSONArray.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}