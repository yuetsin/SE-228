package com.yue.bookie.server.lib.controller;

import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import com.yue.bookie.server.lib.packer.JSONPacker;
import com.yue.bookie.server.lib.service.BookieUtils;
import com.yue.bookie.server.lib.struct.Comment;
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
public class CommentListController {
    @RequestMapping(value = "/comlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String comlist(String isbn) {
        try {
            List<Comment> books = BookieUtils.service.getCommentByIsbn(isbn);
            JSONArray JSONArray = new JSONArray(books);
            System.out.println(JSONArray.toString());
            return String.format("{\"status\": \"ok\", \"data\": %s}", JSONArray.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}
