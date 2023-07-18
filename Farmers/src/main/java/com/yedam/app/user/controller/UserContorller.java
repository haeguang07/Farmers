package com.yedam.app.user.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.user.service.MemberService;
import com.yedam.app.user.vo.MemberVO;

@Controller
public class UserContorller {
	@Autowired
	MemberService memberService;
	
	
	// 로그인 페이지 이동
	@GetMapping("login")
	public String login() {
		return "login/login";
	}

	// 아이디 비밀번호 찾기 이동
	@GetMapping("IDFound")
	public String IdFound() {
		return "login/IDPW";
	}

	// 회원가입 페이지 이동
	@GetMapping("join")
	public String join() {
		return "login/signup";
	}

	@GetMapping("/oauth2/authorization/naver")
	@ResponseBody
	public Map<String,Object> callbackNaver(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return Collections.singletonMap("name", principal.getAttribute("name"));
	}

	// 간편로그인
	@PostMapping("snsLogin")
	public String snsLogin(MemberVO member,HttpServletRequest request) throws ServletException {
		System.out.println(member);
		
		String id = memberService.idFound(member.getEmail());
		if (id == null) {
			member.setPw("test");
			memberService.join(member);
			System.out.println(member);
			member.setPw("test");
		} else {
			member.setId(id);
			member= memberService.getMember(id);
		}
		request.login(member.getId(), member.getPassword());
		return "redirect:/";
	}

}
