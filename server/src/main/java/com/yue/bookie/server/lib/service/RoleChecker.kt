package com.yue.bookie.server.lib.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

object RoleChecker {

    val adminRole = "R_ADMIN"

    @JvmStatic
    fun getRole(): String {
        val principal: UserDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val foundUser = BookieUtils.service.getUserByName(principal.username)

        if (foundUser.size < 1) {
            return ""
        }

        if (!foundUser[0].available) {
            return ""
        }

        val role = BookieUtils.service.getUserRole(foundUser[0])
        if (role!!.size < 1) {
            return ""
        }
        return role[0]
    }
}