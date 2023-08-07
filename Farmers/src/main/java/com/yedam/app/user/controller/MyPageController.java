package com.yedam.app.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.activity.vo.ExpApplyVO;
import com.yedam.app.activity.vo.ExpVO;
import com.yedam.app.activity.vo.SkilledVO;
import com.yedam.app.board.vo.BoardVO;
import com.yedam.app.common.service.AlertService;
import com.yedam.app.common.service.PaymentService;
import com.yedam.app.common.vo.AttachmentVO;
import com.yedam.app.common.vo.PaymentDetailVO;
import com.yedam.app.common.vo.PaymentVO;
import com.yedam.app.farm.vo.FarmLendApplyVO;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.AuctionApplyVO;
import com.yedam.app.market.vo.AuctionVO;
import com.yedam.app.market.vo.CropsSaleVO;
import com.yedam.app.market.vo.FundingVO;
import com.yedam.app.market.vo.MarketVO;
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
	@Autowired
	AlertService alertService;
	@Autowired
	StringEncryptor jasyptStringEncryptor;
	
	BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
	
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

		MemberVO vo = myPageService.checkPassword(memNo);
		boolean result = scpwd.matches(pw, vo.getPw());

		return result;
	}

	// 회원정보 페이지
	@GetMapping("myPage/memberInfo")
	public String memberInfo(String memNo, Model model) {
		MemberVO vo = myPageService.getMemberInfo(memNo);
		
		vo.setZip(jasyptStringEncryptor.decrypt(vo.getZip()));
		vo.setAddr(jasyptStringEncryptor.decrypt(vo.getAddr()));
		vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
		vo.setDetaAddr(jasyptStringEncryptor.decrypt(vo.getDetaAddr()));
		vo.setEmail(jasyptStringEncryptor.decrypt(vo.getEmail()));
		
		model.addAttribute("memberInfo", changCode(vo));

		return "user/myPage/memberInfo/memberInfo";
	}

	// 회원정보 수정 페이지
	@GetMapping("myPage/memberModify")
	public String memberInfoModify(String memNo, Model model) {
		MemberVO vo = myPageService.getMemberInfo(memNo);
		
		vo.setZip(jasyptStringEncryptor.decrypt(vo.getZip()));
		vo.setAddr(jasyptStringEncryptor.decrypt(vo.getAddr()));
		vo.setDetaAddr(jasyptStringEncryptor.decrypt(vo.getDetaAddr()));
		vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
		vo.setEmail(jasyptStringEncryptor.decrypt(vo.getEmail()));
		model.addAttribute("memberInfo", changCode(vo));

		return "user/myPage/memberInfo/memberModify";
	}

	// 회원정보 수정
	@PostMapping("myPage/memberModify")
	public String memberModify(MemberVO vo) {
		System.out.println(vo);
		if(!vo.getPw().equals("")) {	
			System.out.println("test");
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		vo.setPw(scpwd.encode(vo.getPw()));
		}
		if(!vo.getZip().equals("")) {
			vo.setZip(jasyptStringEncryptor.encrypt(vo.getZip()));
		}
		if(!vo.getAddr().equals("")) {
			vo.setAddr(jasyptStringEncryptor.encrypt(vo.getAddr()));
		}
		if(!vo.getDetaAddr().equals("")) {
			vo.setDetaAddr(jasyptStringEncryptor.encrypt(vo.getDetaAddr()));
		}
		if(!vo.getMbl().equals("")) {
			vo.setMbl(jasyptStringEncryptor.encrypt(vo.getMbl()));
		}
		if(!vo.getEmail().equals("")) {
			vo.setEmail(jasyptStringEncryptor.encrypt(vo.getEmail()));
		}
		
		System.out.println(vo.getPw());
		myPageService.modifyMember(vo);
		return "user/myPage/memberInfo/pwCheck";
	}

	// 등업신청 페이지
	@GetMapping("myPage/upgradeMember")
	public String upgradeMemberForm(String data, Model model) {
		Gson gson = new Gson();
		MemberVO vo = gson.fromJson(data, MemberVO.class);
		System.out.println(vo);
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
			System.out.println(vo);
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
	public void refund(PaymentDetailVO vo) {
		System.out.println(vo);
		myPageService.refundProcedure(vo);
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
		
		return list;
	}
	
	//ajax 농지대여 신청 리스트
	@GetMapping("myPage/subFarmLendList")
	@ResponseBody
	public List<FarmLendApplyVO> subFarmLendList(String memNo){
		List<FarmLendApplyVO> list = myPageService.subFarmLendList(memNo);
		
		return list;
	}
	
	//나의 농지대여 신청자 리스트
	@GetMapping("myPage/myFarmLendSubList")
	public String myFarmLendSubList(String boardNo,Model model) {
		FarmLendVO vo = myPageService.myFarmLendInfo(boardNo);
		List<FarmLendApplyVO> list = vo.getApplys();
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		for (FarmLendApplyVO farmLendApplyVO : list) {
			farmLendApplyVO.setMbl(jasyptStringEncryptor.decrypt(farmLendApplyVO.getMbl()));
			farmLendApplyVO.setUplFileName(jasyptStringEncryptor.decrypt(farmLendApplyVO.getUplFileName()));
		}
		return "user/myPage/myActivity/farmLend/myFarmLendSubList";
	}
	
	//농지대여 나의 신청 상세 정보
	@GetMapping("myPage/FarmLendMySubInfo")
	public String FarmLendMySubInfo(String aplNo,Model model) {
		List<FarmLendApplyVO> list = myPageService.mySubInfo(aplNo);
		for (FarmLendApplyVO vo : list) {
			vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
			System.out.println(vo);
			vo.setUplFileName(jasyptStringEncryptor.decrypt(vo.getUplFileName()));
			
		}
		System.out.println(list);
		model.addAttribute("aplInfo", list);
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
	
	//나의 농지대여 신청 수락 및 전체 거절
	@GetMapping("myPage/myFarmApplyStts")
	@ResponseBody
	public void myFarmApplyStts(FarmLendApplyVO vo) {
		myPageService.myFarmAplStts(vo);
		String addr = vo.getAddr();
		String memNo = vo.getMemNo();
		
		AlertVO alr = new AlertVO();
		alr.setAlrtTitle("신청한 농지대여의 신청이 수락되었습니다");
		alr.setAlrtDesct("신청한 '"+ addr + "' 의 농지대여 신청이 수락되었습니다");
		alr.setBoardCtg("n0");
		alr.setMemNo(memNo);
		
		alertService.addAlert(alr);
	}
	
	//나의 농지대여 신청 거절
	@GetMapping("myPage/myFarmApplyCancel")
	@ResponseBody
	public boolean myFarmApplyCancel(String aplNo) {
		return myPageService.updateApplyStts(aplNo);
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

	
	////////////////////////////나의 농촌 bnb 페이지/////////////////////////////
	//나의 bnb 리스트
	@GetMapping("myPage/myBnbList")
	public String myBnbList(String memNo, Model model) {
		List<BnbVO> list = myPageService.myBnbList(memNo);
		model.addAttribute("bnbList", list);
		return "user/myPage/myActivity/bnb/myBnbList";
	}
	
	//나의 bnb 예약 리스트
	@GetMapping("myPage/myBnbApplyList")
	public String myBnbAppleList(String boardNo, Model model) {
		List<PaymentDetailVO> list = myPageService.myBnbRsvList(boardNo);
		System.out.println(list);
		model.addAttribute("payList", list);
		return "user/myPage/myActivity/bnb/myBnbApplyList";
	}
	
	/////////////////////나의 농작물 판매 페이지 ////////////////////////////////
	//나의 농작뭏물 판매 리스트
	@GetMapping("myPage/myCropsSaleList")
	public String myCropsSaleList(String memNo,Model model) {
		List<CropsSaleVO> list = myPageService.myCropsSaleList(memNo);
		System.out.println(list);
		model.addAttribute("cropsList", list);
		return "user/myPage/myActivity/cropsSale/myCropsSaleList";
	}
	
	// 나의 농작물 판매 구매자 리스트
	@GetMapping("myPage/myCropsSalePayList")
	public String myCropsSalePayList (String boardNo, Model model) {
		List<PaymentDetailVO> list = myPageService.myCropsPayList(boardNo);
		System.out.println(list);
		model.addAttribute("payList", list);
		return "user/myPage/myActivity/cropsSale/myCropsSalePayList";
	}
	
	//////////////////////나의 전원마켓 페이지////////////////////////////
	//나의 전원마켓 리스트
	@GetMapping("myPage/myMarketList")
	public String myMarketList (String memNo, Model model) {
		List<MarketVO> list = myPageService.myMarketList(memNo);
		model.addAttribute("marketList", list);
		return "user/myPage/myActivity/myMarket/myMarketList";
	}
	
	//나의 전원마켓 구매자 리스트
	@GetMapping("myPage/myMarketPayList")
	public String myMarketPayList(String boardNo, Model model) {
		List<PaymentDetailVO> list = myPageService.myMarketPayList(boardNo);
		model.addAttribute("payList", list);
		return "user/myPage/myActivity/myMarket/myMarketPayList";
	}
	
	////////////////나의 게시글 페이지/////////////////////////
	//나의 게시글 리스트
	@GetMapping("myPage/myBoardList")
	public String myBoardList(String memNo, Model model) {
		List<BoardVO> list = myPageService.myBoardList(memNo);
		model.addAttribute("boardList", list);
		return "user/myPage/myActivity/myBoard/myBoardList";
	}
	
	//////////////////나의 판매내역 페이지//////////////////
	//판매 내역 리스트
	@GetMapping("myPage/mySaleList")
	public String mySaleList(String memNo,Model model) {
		List<PaymentDetailVO> list = myPageService.mySalesPayList(memNo);
		model.addAttribute("saleList", list);
		System.out.println(list);
		return "user/myPage/saleList/saleList";
	}
	
	//판매 상세 정보 페이지
	@GetMapping("myPage/mySaleInfo")
	public String mySaleInfo(String payDetaNo, Model model) {
		PaymentDetailVO vo = myPageService.mySalesPayInfo(payDetaNo);
		vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
		System.out.println(vo);
		System.out.println('a');
		model.addAttribute("payInfo", vo);
		return "user/myPage/saleList/saleInfo";
	}
	
	//나의 판매 상품 배송 상태 변경
	@GetMapping("myPage/updateShipStts")
	@ResponseBody
	public boolean updateShipStts(PaymentDetailVO vo) {
		return myPageService.updateShipStts(vo);
	}
	
	/////////////////농촌체험하기///////////////////
	//나의 농촌체험 폼
	@GetMapping("myPage/myExpForm")
	public String myExpForm() {
		return "user/myPage/myActivity/myExperience/myExpList";
	}
	
	//나의 농촌체험 등록 리스트
	@GetMapping("myPage/myExpList")
	@ResponseBody
	public List<ExpVO> myExpList(String memNo){
		return myPageService.myExpList(memNo);
	}
	
	//나의 농촌체험 신청 리스트
	@GetMapping("myPage/myExpSubList")
	@ResponseBody
	public List<ExpApplyVO> myExpSubList(String memNo){
		return myPageService.myExpSubList(memNo);
	}
	
	//나의 농촌체험 신청자 리스트
	@GetMapping("myPage/myExpSubPeoList")
	public String myExpSubPeoList(String boardNo,Model model) {
		List<ExpApplyVO> list = myPageService.myExpSubPeoList(boardNo);
		for (ExpApplyVO vo : list) {
			vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
		}
		model.addAttribute("applyList", list);
		return "user/myPage/myActivity/myExperience/myExpSubList";
	}
	
	//나의 농지체험 상세신청정보
	@GetMapping("myPage/myExpSubInfo")
	public String myExpSubInfo(String aplNo, Model model) {
		ExpApplyVO vo = myPageService.myExpSubInfo(aplNo);
		vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
		model.addAttribute("info", vo);
		return "user/myPage/myActivity/myExperience/myExpSubInfo";
	}
	
	//나의 농촌체험 신청 삭제
	@GetMapping("myPage/deleteExpApply")
	@ResponseBody
	public boolean deleteExpApply(String aplNo) {
		return myPageService.deleteExpApply(aplNo) > 0 ;
	}
	
	//나의 농촌체험 신청 수락 및 전체 거절
	@GetMapping("myPage/myExpStts")
	@ResponseBody
	public void myExpSttsUpdate(ExpApplyVO vo) {
		myPageService.myExpAplStts(vo);
		
		String title = vo.getTitle();
		String memNo = vo.getMemNo();
		
		AlertVO arl = new AlertVO();
		arl.setAlrtTitle("신청한 농지체험이 수락되었습니다");
		arl.setAlrtDesct("신청한 농지체험 '"+title+"'이 수락되었습니다");
		arl.setMemNo(memNo);
		arl.setBoardCtg("n5");
		
		alertService.addAlert(arl);
	}
	
	//나의 농촌체험 신청 거절
	@GetMapping("myPage/myExpApplyStts")
	@ResponseBody
	public boolean myExpApplyStts(String aplNo) {
		return myPageService.updateExpApplyStts(aplNo) > 0 ;
	}
	
	////////////////////금손귀농인//////////////////////////
	//나의 금손귀농인 폼
	@GetMapping("myPage/mySkilledForm")
	public String mySkilledForm() {
		return "user/myPage/myActivity/mySkilled/mySkilledList";
	}
	
	//나의 금손귀농인 리스트
	@GetMapping("myPage/mySkilledList")
	@ResponseBody
	public List<SkilledVO> mySkilledList(SkilledVO vo){
		System.out.println(vo);
		List<SkilledVO> list = myPageService.mySkilledList(vo);
		return list;	
	}
	
	//나의 금손귀농인 신청 리스트
	@GetMapping("myPage/mySkilledSubList")
	@ResponseBody
	public List<SkilledVO> mySkilledSubList(String memNo){
		List<SkilledVO> list = myPageService.mySkilledSubList(memNo);
		for (SkilledVO vo : list) {
			vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
		}
		System.out.println(list);
		return list;
	}
	
	//나의 금손귀농인 구직 리스트
	@GetMapping("myPage/myJobSearchList")
	public String myJobSearchList(String boardNo,Model model) {
		List<SkilledVO> list = myPageService.myJobSearchList(boardNo);
		for (SkilledVO vo : list) {
			vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
		}
		model.addAttribute("subList", list); 
		System.out.println(list);
		return "user/myPage/myActivity/mySkilled/myJobSearchList";
	}
	
	//나의 금손귀농인 신청 정보 페이지
	@GetMapping("myPage/mySkilledSubInfo")
	public String mySkilledSubInfo(String aplNo, Model model) {
		SkilledVO vo = myPageService.mySkilledSubInfo(aplNo);
		model.addAttribute("SubInfo", vo);
		vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
		System.out.println(vo);
		return "user/myPage/myActivity/mySkilled/mySkilledSubInfo";
	}
	
	//나의 금손귀농인 신청 수락
	@PostMapping("myPage/mySkilledSubAccept")
	@ResponseBody
	public void mySkilledSubAccept(SkilledVO vo) {
		System.out.println(vo);
		myPageService.SKilledSubAccept(vo);
	}
	
	//나의 금손귀농인 신청 거절
	@PostMapping("myPage/skilledSubRefuse")
	@ResponseBody
	public boolean skilledSubRefuse(String aplNo) {
		return myPageService.skilledSubRefuse(aplNo);
	}
	
	//금손 귀농인 나의 신청 삭제
	@PostMapping("myPage/deleteSKilledSub")
	@ResponseBody
	public boolean deleteSkilledApply(String aplNo) {
		return myPageService.deleteSkilledApply(aplNo);
	}

	

	
}
