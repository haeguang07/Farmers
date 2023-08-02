package com.yedam.app.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yedam.app.user.service.MemberService;

@Controller
public class UserContorller {
	@Autowired
	MemberService memberService;

	// 로그인 페이지 이동
	@GetMapping("login")
	public String login(@PathVariable(required = false) String error,Model model, HttpServletRequest request) {
		String uri = request.getHeader("Referer");
	    if (uri != null && !uri.contains("/login")) {
	        request.getSession().setAttribute("prevPage", uri);
	    }
		System.out.println(error);
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
