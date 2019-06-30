package com.yue.bookie.server.lib.controller
import org.json.JSONObject
import com.yue.bookie.server.lib.service.BookieUtils
import com.yue.bookie.server.lib.struct.Order
import com.yue.bookie.server.lib.struct.OrderItem
import com.yue.bookie.server.lib.struct.User
import com.yue.bookie.server.lib.utils.RoleChecker
import org.json.JSONArray

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

fun json(build: JsonObjectBuilder.() -> Unit): JSONObject {
    val builder = JsonObjectBuilder()
    builder.build()
    return builder.json
}

class JsonObjectBuilder {
    val json = JSONObject()

    infix fun <T> String.To(value: T) {
        json.put(this, value)
    }
}

@RestController
class GetAllOrdersController {
    @RequestMapping(value = ["/admin/allorders"], method = [RequestMethod.GET], produces = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun allOrders(): String {
        if (RoleChecker.getRole() != RoleChecker.adminRole) {
            return "{\"status\": \"unauthorized\"}"
        }
        val resultJson =
                json {
                    "status" To "ok"
                    "values" To JSONArray().apply {  }
                }
        try {
            val orders: List<Order> = BookieUtils.service.allOrders
            for (order: Order in orders) {
                val orderItems = BookieUtils.service.getOrderItems(order.billUuid)
                for (orderItem in orderItems) {
                    (resultJson["values"] as JSONArray).put(json {
                        "userId" To order.userId
                        "userName" To (BookieUtils.service.getUserById(order.userId) as User).name
                        "bookIsbn" To orderItem.isbn
                        "count" To orderItem::count
                        "timestamp" To order.time
                    })
                }
            }
            return resultJson.toString()
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "{\"status\": \"internal_error\"}"
        }
    }
}