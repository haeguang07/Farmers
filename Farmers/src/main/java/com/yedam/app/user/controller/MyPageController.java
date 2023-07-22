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

import com.yedam.app.common.service.PaymentService;
import com.yedam.app.common.vo.PaymentDetailVO;
import com.yedam.app.common.vo.PaymentVO;
import com.yedam.app.farm.vo.FarmLendApplyVO;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.AuctionApplyVO;
import com.yedam.app.market.vo.AuctionVO;
import com.yedam.app.market.vo.FundingVO;
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
	@GetMapping("myPage/myPage")
	public String myPage() {
		return "user/myPage/memberInfo/pwCheck";
	}

	// 마이페이지 비번 체크
	@GetMapping("myPage/myPwCheck")
	@ResponseBody
	public boolean pwCheck(String pw, String memNo) {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();

		MemberVO vo = myPageService.checkPassword(memNo);
		boolean result = scpwd.matches(pw, vo.getPw());

		return result;
	}

	// 회원정보 페이지
	@GetMapping("myPage/memberInfo")
	public String memberInfo(String memNo, Model model) {
		System.out.println(memNo);
		MemberVO vo = myPageService.getMemberInfo(memNo);
		System.out.println(vo);

		model.addAttribute("memberInfo", changCode(vo));

		return "user/myPage/memberInfo/memberInfo";
	}

	// 회원정보 수정 페이지
	@GetMapping("myPage/memberModify")
	public String memberInfoModify(String memNo, Model model) {
		MemberVO vo = myPageService.getMemberInfo(memNo);
		model.addAttribute("memberInfo", changCode(vo));

		return "user/myPage/memberInfo/memberModify";
	}

	// 회원정보 수정
	@PostMapping("myPage/memberModify")
	public String memberModify(MemberVO vo) {
		System.out.println(vo);
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		
		vo.setPw(scpwd.encode(vo.getPw()));
		System.out.println(vo.getPw());
		myPageService.modifyMember(vo);
		return "user/myPage/memberInfo/pwCheck";
	}

	// 등업신청 페이지
	@GetMapping("myPage/upgradeMember")
	public String upgradeMemberForm(MemberVO vo, Model model) {
	
		model.addAttribute("member", vo);
		return "user/myPage/memberInfo/upgrade";
	}

	// 등업신청 기능
	@PostMapping("myPage/upgradeMember")
	@ResponseBody
	public boolean upgradeMember(AttachVO vo) {
		System.out.println(vo);
		return myPageService.upgradeGrade(vo);
	}

	// 포인트내역 페이지
	@GetMapping("myPage/pointHistory")
	public String pointHistory(String memNo, Model model) {
		List<PointsVO> list = myPageService.pointHistory(memNo);
		for (PointsVO vo : list) {
			if(vo.getPntStts().equals("use")) {
				vo.setPntStts("사용");
			}else if(vo.getPntStts().equals("get")) {
				vo.setPntStts("적립");
			}
		}
		model.addAttribute("points", list);
		return "user/myPage/memberInfo/pointHistory";
	}

	// 회원탈퇴
	@GetMapping("myPage/secession")
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
	@GetMapping("myPage/myPayList")
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
	@GetMapping("myPage/myPayDetail")
	public String myPayDetail(String payNo, Model model) {
		PaymentVO vo = paymentService.getPayList(payNo);
		List<PaymentDetailVO> list = vo.getPaymentDetails();
		for (PaymentDetailVO paymentDetailVO : list) {
			paymentService.getProductInfo(paymentDetailVO);
		}
		System.out.println(list);
		model.addAttribute("payInfo", vo);
		model.addAttribute("prodInfo", list);
		return "user/myPage/paymentList/paymentDetail";
	}
	
	//결제 환불 처리
	@GetMapping("myPage/refund")
	@ResponseBody
	public boolean refund(String payNo) {
		System.out.println(payNo);
		return myPageService.refund(payNo);
	}
	

	////////////////////// 나의 문의//////////////////////
	// 나의 문의 페이지
	@GetMapping("myPage/myInquiry")
	public String myInquiry(String memNo, Model model) {
		List<InquiryVO> list = myPageService.myInquiry(memNo);
		System.out.println(list);
		model.addAttribute("inqList", list);
		return "user/myPage/inquiry/myInquiry";
	}

	// 문의 등록 페이지
	@GetMapping("myPage/addInpuiry")
	public String addInquiryForm() {
		return "user/myPage/inquiry/addInquiryForm";
	}

	// 문의 등록
	@PostMapping("myPage/addInpuiry")
	@ResponseBody
	public boolean addInquiry(InquiryVO vo) {
		System.out.println(vo);
		return myPageService.addInquiry(vo);
	}

	// 문의 상세 페이지
	@GetMapping("myPage/inquiryInfo")
	public String inquiryInfo(String inqNo, Model model) {
		InquiryVO vo = myPageService.myInquiryInfo(inqNo);
		model.addAttribute("inqInfo", vo);
		return "user/myPage/inquiry/inquiryDetail";
	}

	/////////////////// 알림 페이지///////////////////
	// 알림 리스트 페이지
	@GetMapping("myPage/alertList")
	public String alertList(String memNo, Model model) {
		List<AlertVO> list = myPageService.alertList(memNo);
		for (AlertVO vo : list) {
			if (vo.getAlrtStts().equals("L1")) {
				vo.setAlrtStts("미열람");
			} else {
				vo.setAlrtStts("열람");
			}
		}
		model.addAttribute("alertList", list);
		return "user/myPage/alert/alertList";
	}

	// 알림 상세 페이지
	@GetMapping("myPage/alertInfo")
	public String alretInfo(String alrtNo, Model model) {
		System.out.println(alrtNo);
		AlertVO vo = myPageService.alertInfo(alrtNo);
		System.out.println(vo);
		model.addAttribute("alertInfo", vo);
		return "user/myPage/alert/alertDetail";
	}

	// 알림 상태 변경
	@GetMapping("myPage/changeAlretStts")
	@ResponseBody
	public boolean changeAlertStts(String alrtNo) {
		System.out.println(alrtNo);
		return myPageService.updateAlrtStts(alrtNo);
	}

	/////////////////////// 나의 활동(농지대여)////////////////////
	// 농지대여 리스트 페이지
	@GetMapping("myPage/myFarmLendListForm")
	public String myFarmLendListForm() {
		return "user/myPage/myActivity/farmLend/myFarmLendList";
	}
	
	//ajax 농지대여 등록 리스트
	@GetMapping("myPage/myFarmLendList")
	@ResponseBody
	public List<FarmLendVO> myFarmLendList(String memNo) {
		List<FarmLendVO> list =  myPageService.myFarmLendList(memNo);
		for (FarmLendVO farmLendVO : list) {
			
		}
		return list;
	}
	
	//ajax 농지대여 신청 리스트
	@GetMapping("myPage/subFarmLendList")
	@ResponseBody
	public List<FarmLendApplyVO> subFarmLendList(String memNo){
		return myPageService.subFarmLendList(memNo);
	}
	
	//나의 농지대여 신청자 리스트
	@GetMapping("myPage/myFarmLendSubList")
	public String myFarmLendSubList(String boardNo,Model model) {
		FarmLendVO vo = myPageService.myFarmLendInfo(boardNo);
		List<FarmLendApplyVO> list = vo.getApplys();
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		return "user/myPage/myActivity/farmLend/myFarmLendSubList";
	}
	
	//농지대여 나의 신청 상세 정보
	@GetMapping("myPage/FarmLendMySubInfo")
	public String FarmLendMySubInfo(String aplNo,Model model) {
		FarmLendApplyVO vo = myPageService.mySubInfo(aplNo);
		model.addAttribute("aplInfo", vo);
		return "user/myPage/myActivity/farmLend/farmLendMySubInfo";
	}
	
	//나의 농지대여 신청 삭제
	@GetMapping("myPage/deleteMyFarmSub")
	@ResponseBody
	public boolean deleteMyFarmSub(String aplNo) {
		System.out.println("A");
		System.out.println(aplNo);
		return myPageService.deleteMyFarmSub(aplNo);
	}
	
	//////////////////////경매장 페이지///////////////////////
	//경매장 리스트 페이지
	@GetMapping("myPage/myAuctionForm")
	public String myAuctionForm() {
		return "user/myPage/myActivity/auction/myAuctionList";
	}
	
	//내 경매 등록 리스트
	@GetMapping("myPage/myAuctionList")
	@ResponseBody
	public List<AuctionVO> myAuctionList(String memNo){
		System.out.println(memNo);
		System.out.println(myPageService.myActionList(memNo));
		return myPageService.myActionList(memNo);	
	}
	
	//내 입찰 경매 리스트
	@GetMapping("myPage/myBidList")
	@ResponseBody
	public List<AuctionVO> myBidList(String memNo){
		return myPageService.myBidList(memNo);
	}
	
	//내 경매의 입찰 리스트
	@GetMapping("myPage/myAuctionBidForm")
	public String myAuctionBidForm(String boardNo,Model model) {
		List<AuctionApplyVO> list = myPageService.myAuctionBidList(boardNo);
		System.out.println(list);
		model.addAttribute("bidList", list);
		return "user/myPage/myActivity/auction/myAuctionBidList";
	}
	
	////////////////나의 펀딩 페이지////////////////////////////
	//나의 펀딩 리스트
	@GetMapping("myPage/myFundingForm")
	public String myFundingForm(String memNo,Model model) {
		List<FundingVO> list = myPageService.myFundingList(memNo);
		model.addAttribute("fundingList", list);
		return "user/myPage/myActivity/funding/myFundingList";
	}
	
	//나의 펀딩 구매 목록
	@GetMapping("myPage/myFundingSubList")
	public String myFundingSubList(String boardNo,Model model) {
		List<PaymentDetailVO> list = myPageService.myFundingPayList(boardNo);
		System.out.println(list);
		model.addAttribute("payList", list);
		return "user/myPage/myActivity/funding/myFundingSubList";
	}

}
