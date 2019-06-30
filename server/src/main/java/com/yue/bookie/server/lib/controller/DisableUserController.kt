package com.yue.bookie.server.lib.controller

import com.yue.bookie.server.lib.service.BookieUtils
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DisableUserController {
    @PreAuthorize("hasAuthority('R_ADMIN')")
    @RequestMapping(value = ["/admin/disableuser"], method = [RequestMethod.POST], produces = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun disableUser(userId: Int): String
    {
        try {
            BookieUtils.service.disableUser(userId)
            return "{\"status\": \"ok\"}"
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "{\"status\": \"internal_error\"}"
        }
    }
}