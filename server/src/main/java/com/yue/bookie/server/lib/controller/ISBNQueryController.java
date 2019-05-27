package com.yue.bookie.server.lib.controller;

import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;
import com.yue.bookie.server.lib.packer.JSONPacker;
import com.yue.bookie.server.lib.service.BookieUtils;
import com.yue.bookie.server.lib.struct.Book;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

@RestController
public class ISBNQueryController {
    @RequestMapping(value = "/isbn", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @CrossOrigin
    @ResponseBody
    public String isbn(String isbn) {
        try {
            List<Book> books = BookieUtils.service.getBookByIsbn(isbn);
            JSONArray JSONArray = new JSONArray(books);
            System.out.println(JSONArray.toString());
            return String.format("{\"status\": \"ok\", \"data\": %s}", JSONArray.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}