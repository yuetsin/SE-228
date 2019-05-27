package com.yue.bookie.server.lib.controller;

import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import com.yue.bookie.server.lib.service.BookieUtils;
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
            BookieUtils.service.addComments(isbn, content);
            return "{\"status\": \"ok\"}";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}
