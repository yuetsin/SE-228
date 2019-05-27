package com.yue.bookie.server.lib.controller;


import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.config.SecurityConfig;

import com.yue.bookie.server.lib.service.BookieUtils;
import com.yue.bookie.server.lib.struct.Book;
import com.yue.bookie.server.lib.struct.User;
import com.yue.bookie.server.lib.utils.ValidationChecker;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;
import java.util.Objects;


@RestController
public class UserRegisterController {
    @RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String reg(String username, String password, String confirmPassword) {
        System.out.println("username = " + username + " password = " + password + " confirm = " + confirmPassword);
        if (!password.equals(confirmPassword)) {
            return "{\"status\": \"confirmation_error\"}";
        }
        if (!(ValidationChecker.isLegalPassword(password) && ValidationChecker.isLegalUserName(username))) {
            return "{\"status\": \"bad_format\"}";
        }
        try {
            List<User> existedUser = BookieUtils.service.getUserByName(username);
            if (existedUser.size() != 0) {
                return "{\"status\": \"user_already_exist\"}";
            }

            BookieUtils.service.registerUser(username, password);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "{\"status\": \"failed\"}";
    }
}