package com.yue.bookie.server.lib.controller

import com.yue.bookie.server.lib.service.BookieUtils
import com.yue.bookie.server.lib.utils.RoleChecker
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception


@RestController
class BookInsertController {
    @RequestMapping(value = ["/admin/addbook"], method = [RequestMethod.POST], produces = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun addBook(title: String, author: String, type: String, description: String, storage: Int, coverId: String, isbn: String, price: String, coupon_price: String): String {
        if (RoleChecker.getRole() != RoleChecker.adminRole) {
            return "{\"status\": \"unauthorized\"}"
        }

        if (storage < 0) {
            return "{\"status\": \"bad_input\"}"
        }

        val priceNum = price.toFloatOrNull()
        val couponPriceNum = coupon_price.toFloatOrNull()

        if (priceNum == null || couponPriceNum == null) {
            return "{\"status\": \"bad_input\"}"
        }

        return try {
            BookieUtils.service.addBook(title, author, type, description, storage, coverId, isbn, priceNum, couponPriceNum)
            "{\"status\": \"ok\"}"
        } catch (e: Exception) {
            e.printStackTrace()
            "{\"status\": \"internal_error\"}"
        }
    }
}