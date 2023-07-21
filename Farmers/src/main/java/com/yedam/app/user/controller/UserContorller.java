package com.yedam.app.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.user.service.AdminService;
import com.yedam.app.user.service.MemberService;

@Controller
public class UserContorller {
	@Autowired
	MemberService memberService;
	@Autowired
	AdminService adminService;
	
	
	
	//관리자 페이지
	@GetMapping("/admin")
	public String memberManagement() {
		return "user/admin/admin";
	}
	
	// 로그인 페이지 이동
	@GetMapping("login")
	public String login() {
		return "user/login/login";
	}

	// 아이디 비밀번호 찾기 이동
	@GetMapping("IDFound")
	public String IdFound() {
		return "user/login/IDPW";
	}

	// 회원가입 페이지 이동
	@GetMapping("join")
	public String join() {
		return "user/login/signup";
	}
	
	

}
