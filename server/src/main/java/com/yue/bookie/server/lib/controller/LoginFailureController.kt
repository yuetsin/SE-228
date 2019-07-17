package com.yue.bookie.server.lib.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
open class LoginFailureController {
    @RequestMapping(value = ["/failure"], method = [RequestMethod.GET], produces = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun loginFailure(): String {
        return "{\"status\": \"failed_to_login\"}"
    }
}