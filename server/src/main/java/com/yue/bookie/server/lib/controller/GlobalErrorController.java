package com.yue.bookie.server.lib.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class GlobalErrorController implements ErrorController {
    private final static String PATH = "/error";
    @Override
    @RequestMapping(PATH)
    public String getErrorPath() {
        return "index";
    }
}