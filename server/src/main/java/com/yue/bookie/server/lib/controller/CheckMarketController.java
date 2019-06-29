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
import org.w3c.dom.NodeList;

import java.sql.*;
import java.util.List;
import java.util.Vector;

@RestController
public class CheckMarketController {
    @RequestMapping(value = "/market", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String market() {
        try {
            List<Cart> currentUserCart = BookieUtils.service.getUserCart();

            String builtStr = "";
            List<String> partial = new Vector<>();
            for (Cart cart : currentUserCart) {
                partial.add(String.format("{\"isbn\": \"%s\", \"count\": %d, \"author\": \"%s\", \"title\": \"%s\"}", cart.isbn, cart.count, cart.author, cart.title));
            }
            System.out.println(builtStr);
            return String.format("{\"status\": \"ok\", \"data\": [%s]}",String.join(", ", partial));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}