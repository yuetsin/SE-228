package com.yue.bookie.server.lib.controller

import com.yue.bookie.server.lib.service.BookieUtils
import com.yue.bookie.server.lib.utils.RoleChecker
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
open class EnableBookController {
    @RequestMapping(value = ["/admin/enablebook"], method = [RequestMethod.POST], produces = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun enableBook(isbn: String): String
    {
        if (RoleChecker.getRole() != RoleChecker.adminRole) {
            return "{\"status\": \"unauthorized\"}"
        }
        return try {
            System.out.println(isbn)
            BookieUtils.service.enableBook(isbn)
            "{\"status\": \"ok\"}"
        } catch (ex: Exception) {
            ex.printStackTrace()
            "{\"status\": \"internal_error\"}"
        }
    }
}