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
	//아이디 비밀번호 찾기 이동
	@GetMapping("IDFound")
	public String IdFound() {
		return "login/IDPW";
	}
	//회원가입 페이지 이동
	@GetMapping("join")
	public String join() {
		return "login/signup";
	}
	
	
	
}
