package com.yue.bookie.server.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 请求页面分发，注意和WebMvcConfig的对比，功能类似
 * @author Veiking
 */
@Controller
public class PageController {
	
	@RequestMapping("/admin")
	@PreAuthorize("hasAuthority('R_ADMIN')")
	public String admin(Model model, String tt) {
		return "admin";
	}
	
	@RequestMapping("/hello")
	public String hello(Model model, String tt) {
		return "hello";
	}


	@RequestMapping("/me")
	@ResponseBody
	public String me(Model model, String tt) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "Hello! You're " + userDetails.getUsername();
	}
}
