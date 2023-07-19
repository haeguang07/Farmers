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
import com.yedam.app.common.service.PaymentService;
import com.yedam.app.common.vo.PaymentDetailVO;
import com.yedam.app.common.vo.PaymentVO;
import com.yedam.app.user.service.MyPageService;
import com.yedam.app.user.vo.AlertVO;
import com.yedam.app.user.vo.AttachVO;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.PointsVO;

@Controller
public class MyPageController {
	@Autowired
	MyPageService myPageService;
	@Autowired
	PaymentService paymentService;

	////////////// 회원정보 페이지//////////////
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
	public String memberModify(MemberVO vo) {
		System.out.println(vo);
		System.out.println(vo.getPw());
		myPageService.modifyMember(vo);
		return "user/myPage/memberInfo/pwCheck";
	}

	// 등업신청 페이지
	@GetMapping("upgradeMember")
	public String upgradeMemberForm(String member, Model model) {
		Gson gson = new Gson();
		MemberVO vo = gson.fromJson(member, MemberVO.class);
		System.out.println(vo);
		model.addAttribute("member", vo);
		return "user/myPage/memberInfo/upgrade";
	}

	// 등업신청 기능
	@PostMapping("upgradeMember")
	@ResponseBody
	public boolean upgradeMember(AttachVO vo) {
		System.out.println(vo);
		return myPageService.upgradeGrade(vo);
	}

	// 포인트내역 페이지
	@GetMapping("pointHistory")
	public String pointHistory(String memNo, Model model) {
		List<PointsVO> list = myPageService.pointHistory(memNo);
		model.addAttribute("points", list);
		return "user/myPage/memberInfo/pointHistory";
	}

	// 회원탈퇴
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

	////////////// 결제 내역 페이지 //////////////
	// 결제내역
	@GetMapping("myPayList")
	public String myPayList(String memNo, Model model) {
		List<PaymentVO> list = myPageService.myPayNo(memNo);
		List<PaymentVO> getInfoList = new ArrayList<PaymentVO>();
		for (PaymentVO vo : list) {
			myPageService.myPayList(vo);
			getInfoList.add(vo);
		}
		model.addAttribute("payList", getInfoList);
		return "user/myPage/paymentList/paymentList";
	}

	// 결제상세내역
	@GetMapping("myPayDetail")
	public String myPayDetail(String payNo, Model model) {
		PaymentVO vo = paymentService.getPayList(payNo);
		List<PaymentDetailVO> list = vo.getPaymentDetails();
		for (PaymentDetailVO paymentDetailVO : list) {
			String ship = paymentDetailVO.getShipStts();
			if (ship.equals("B0")) {
				paymentDetailVO.setShipStts("결제대기");
			} else if (ship.equals("B1")) {
				paymentDetailVO.setShipStts("결제완료");
			} else if (ship.equals("B2")) {
				paymentDetailVO.setShipStts("배송 전");
			} else if (ship.equals("B3")) {
				paymentDetailVO.setShipStts("배송 중");
			} else if (ship.equals("B4")) {
				paymentDetailVO.setShipStts("배송 완료");
			} else if (ship.equals("B5")) {
				paymentDetailVO.setShipStts("환불 대기");
			} else if (ship.equals("B6")) {
				paymentDetailVO.setShipStts("환불 완료");
			}
			paymentService.getProductInfo(paymentDetailVO);
		}
		System.out.println(list);
		model.addAttribute("payInfo", vo);
		model.addAttribute("prodInfo", list);
		return "user/myPage/paymentList/paymentDetail";
	}

	////////////////////// 나의 문의//////////////////////
	// 나의 문의 페이지
	@GetMapping("myInquiry")
	public String myInquiry(String memNo, Model model) {
		List<InquiryVO> list = myPageService.myInquiry(memNo);
		model.addAttribute("inqList", list);
		return "user/myPage/inquiry/myInquiry";
	}

	// 문의 등록 페이지
	@GetMapping("addInpuiry")
	public String addInquiryForm() {
		return "user/myPage/inquiry/addInquiryForm";
	}

	// 문의 등록
	@PostMapping("addInpuiry")
	@ResponseBody
	public boolean addInquiry(InquiryVO vo) {
		System.out.println(vo);
		return myPageService.addInquiry(vo);
	}
	
	//문의 상세 페이지
	@GetMapping("inquiryInfo")
	public String inquiryInfo(String inqNo,Model model) {
		InquiryVO vo = myPageService.myInquiryInfo(inqNo);
		model.addAttribute("inqInfo", vo);
		return "user/myPage/inquiry/inquiryDetail";
	}
	
	///////////////////알림 페이지///////////////////
	@GetMapping("alertList")
	public String alertList(String memNo,Model model){
		List<AlertVO> list = myPageService.alertList(memNo);
		for (AlertVO vo : list) {
			if(vo.getAlrtStts().equals("L1")) {
				vo.setAlrtStts("미열람");
			}else {
				vo.setAlrtStts("열람");
			}
		}
		model.addAttribute("alertList", list);
		return "user/myPage/alert/alertList";
	}
	
	@GetMapping("alertInfo")
	public String alretInfo(String alrtNo,Model moedel) {
		return "";
	}
}
