package com.yedam.app.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yedam.app.common.vo.PaymentVO;
import com.yedam.app.user.service.MyPageService;
import com.yedam.app.user.vo.AttachVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.PointsVO;

@Controller
public class MyPageController {
	@Autowired
	MyPageService myPageService;
	
	//////////////회원정보 페이지//////////////
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
		System.out.println(vo.getPw());
		myPageService.modifyMember(vo);
		return "user/myPage/memberInfo/pwCheck";
	}
	
	//등업신청 페이지
	@GetMapping("upgradeMember")
	public String upgradeMemberForm(String member,Model model) {
		Gson gson = new Gson();
		MemberVO vo = gson.fromJson(member, MemberVO.class);
		System.out.println(vo);
		model.addAttribute("member", vo);
		return "user/myPage/memberInfo/upgrade";
	}
	
	//등업신청 기능
	@PostMapping("upgradeMember")
	@ResponseBody
	public boolean upgradeMember(AttachVO vo) {
		System.out.println(vo);
		return myPageService.upgradeGrade(vo);
	}
	
	//포인트내역 페이지
	@GetMapping("pointHistory")
	public String pointHistory(String memNo,Model model) {
		List<PointsVO> list = myPageService.pointHistory(memNo);
		model.addAttribute("points", list);
		return "user/myPage/memberInfo/pointHistory";
	}
	
	//회원탈퇴
	@GetMapping("secession")
	@ResponseBody
	public boolean secession(String memNo) {
		boolean result = myPageService.secession(memNo);
		System.out.println(result);
		return result;
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
//		String gender = vo.getGen();
//		if (gender.equals("a0")) {
//			vo.setGen("남성");
//		} else if (gender.equals("a1")) {
//			vo.setGen("여성");
//		}
		return vo;
	}
	//////////////회원정보 페이지 끝//////////////
	
	//////////////결제 내역 페이지 //////////////
	@GetMapping("myPayList")
	public String myPayList(String memNo,Model model) {
		System.out.println(memNo);
		List<PaymentVO> list = myPageService.myPayNo(memNo);
		List<PaymentVO> getInfoList = new ArrayList<PaymentVO>();
		for (PaymentVO vo : list) {
			System.out.println(vo);
			myPageService.myPayList(vo);
			System.out.println(vo);
			getInfoList.add(vo);
		}
		model.addAttribute("payList", getInfoList);
		return "user/myPage/paymentList/paymentList";
	}
	
	
	
	//////////////결제 내역 페이지 끝//////////////
}
