package com.yedam.app.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserContorller {
	
	
	
	
	
	
	
	
	//로그인 페이지 이동
	@GetMapping("login")
	public String login() {
		return "login/login";
	}
	
	
	
}
