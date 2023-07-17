package com.yedam.app.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.user.service.MyPageService;
import com.yedam.app.user.vo.MemberVO;

@Controller
public class MyPageController {
	@Autowired
	MyPageService myPageService;

	// 마이페이지 (기본화면)
	@GetMapping("myPage")
	public String myPage() {
		return "user/myPage/memberInfo/pwCheck";
	}

	// 마이페이지 비번 체크
	@GetMapping("myPwCheck")
	@ResponseBody
	public boolean pwCheck(String pw, String memNo) {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		System.out.println(pw);

		MemberVO vo = myPageService.checkPassword(memNo);
		System.out.println(vo.getPw());
		boolean result = scpwd.matches(pw, vo.getPw());
		System.out.println(result);

		return result;
	}

	// 회원정보 페이지
	@GetMapping("memberInfo")
	public String memberInfo(String memNo, Model model) {
		MemberVO vo = myPageService.getMemberInfo(memNo);
		
		model.addAttribute("memberInfo", changCode(vo));

		return "user/myPage/memberInfo/memberInfo";
	}

	// 회원정보 수정 페이지
	@GetMapping("memberModify")
	public String memberInfoModify(String memNo, Model model) {
		MemberVO vo = myPageService.getMemberInfo(memNo);
		model.addAttribute("memberInfo", changCode(vo));

		return "user/myPage/memberInfo/memberModify";
	}
	
	// 회원정보 수정
	@PostMapping("memberModify")
	public String memberModify(MemberVO vo){
		System.out.println(vo);
		if(vo.getPw().equals("")) {
			System.out.println("aaaaaaa");
		}
		return "user/myPage/memberInfo/pwCheck";
	}

	// 성별코드변환
	public MemberVO changCode(MemberVO vo) {
		// 회원등급 코드 변환
		String grd = vo.getMemGrd();
		if (grd.equals("b1")) {
			vo.setMemGrd("정회원");
		} else if (grd.equals("b2")) {
			vo.setMemGrd("준회원");
		}

		// 성별 코드변환
		String gender = vo.getGen();
		if (gender.equals("a0")) {
			vo.setGen("남성");
		} else if (gender.equals("a1")) {
			vo.setGen("여성");
		}
		return vo;
	}

}
