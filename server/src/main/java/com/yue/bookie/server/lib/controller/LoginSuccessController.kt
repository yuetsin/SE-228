package com.yue.bookie.server.lib.controller

import com.yue.bookie.server.lib.service.BookieUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails


@RestController
open class LoginSuccessController {
    @RequestMapping(value = ["/ok"], method = [RequestMethod.GET], produces = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun loginSuccess(): String {
        val principal: UserDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val foundUser = BookieUtils.service.getUserByName(principal.username)

        if (foundUser.size < 1) {
            return "{\"status\": \"failed_to_login\"}"
        }

        if (!foundUser[0].available) {
            return "{\"status\": \"account_locked\"}"
        }

        val role = BookieUtils.service.getUserRole(foundUser[0])
        if (role!!.size < 1) {
            return "{\"status\": \"account_unauthorized\"}"
        }
        return "{\"status\": \"login_successfully\", \"role\": \"" + role[0] + "\"}"
    }
}