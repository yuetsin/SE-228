package com.yue.bookie.server.lib.controller

import com.yue.bookie.server.lib.service.BookieUtils
import com.yue.bookie.server.lib.utils.RoleChecker
import org.json.JSONArray
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccessAllBookController {
    @RequestMapping(value = ["/admin/allbook"], method = [RequestMethod.GET], produces = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun getAllBooks(): String {
        if (RoleChecker.getRole() != RoleChecker.adminRole) {
            return "{\"status\": \"unauthorized\"}"
        }

        return try {
            val books = BookieUtils.service.getAllBooks()
            val jsonArray = JSONArray(books)
            println(jsonArray.toString())
            String.format("{\"status\": \"ok\", \"data\": %s}", jsonArray.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            "{\"status\": \"internal_error\"}"
        }
    }
}