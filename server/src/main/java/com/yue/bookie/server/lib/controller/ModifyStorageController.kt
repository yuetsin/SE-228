package com.yue.bookie.server.lib.controller

import com.yue.bookie.server.lib.service.BookieUtils
import com.yue.bookie.server.lib.service.RoleChecker
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
open class ModifyStorageController {
    @RequestMapping(value = ["/admin/setstorage"], method = [RequestMethod.POST], produces = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun modifyStorage(isbn: String, storage: Int): String {
        if (RoleChecker.getRole() != RoleChecker.adminRole) {
            return "{\"status\": \"unauthorized\"}"
        }
        try {
            BookieUtils.service.setStorage(isbn, storage)
            return "{\"status\": \"ok\"}"
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "{\"status\": \"internal_error\"}"
        }
    }
}