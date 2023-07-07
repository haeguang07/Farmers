package com.yedam.app.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.user.service.MemberService;
import com.yedam.app.user.vo.MemberVO;

@RestController
public class LoginRestController {
	
	@Autowired
	MemberService memberService;
	
	//로그인 기능
	@PostMapping("login")
	public Map<String, Object> login(MemberVO vo, HttpServletRequest request){
		
		vo=memberService.login(vo);
		Map<String, Object> map= new HashMap<>();
		if(vo.getMemNo()!=null) {
			map.put("result", "success");
			HttpSession session = request.getSession();
			session.setAttribute("mem", vo);
		}else {
			map.put("result", "fail");
		}
		return map;
	}
	
}
