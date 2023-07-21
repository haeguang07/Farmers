package com.yedam.app.user.service;

import java.util.List;

import com.yedam.app.common.vo.PaymentVO;
import com.yedam.app.farm.vo.FarmLendApplyVO;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.AuctionVO;
import com.yedam.app.user.vo.AlertVO;
import com.yedam.app.user.vo.AttachVO;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.PointsVO;

public interface MyPageService {
	// 비밀번호 확인
	public MemberVO checkPassword(String memNo);

	// 회원정보 가져오기
	public MemberVO getMemberInfo(String memNo);

	// 회원정보수정
	public void modifyMember(MemberVO vo);

	// 등업신청
	public boolean upgradeGrade(AttachVO vo);

	// 포인트 내역
	public List<PointsVO> pointHistory(String memNo);

	// 회원탈퇴
	public boolean secession(String memNo);

	// 문의내역 조회
	public List<InquiryVO> myInquiry(String memNo);

	// 결제번호 조회
	public List<PaymentVO> myPayNo(String memNo);

	// 결제내역 조회
	public PaymentVO myPayList(PaymentVO vo);

	// 결제환불
	public boolean refund(String payNo);

	// 문의 등록
	public boolean addInquiry(InquiryVO vo);

	// 문의 상세 정보
	public InquiryVO myInquiryInfo(String inqNo);

	// 알림 리스트
	public List<AlertVO> alertList(String memNo);

	// 알림 상세
	public AlertVO alertInfo(String alrtNo);

	// 알림 열람 변경
	public boolean updateAlrtStts(String alrtNo);

	// 농지대여 등록 리스트
	public List<FarmLendVO> myFarmLendList(String memNo);

	// 농지대여 신청 리스트
	public List<FarmLendApplyVO> subFarmLendList(String memNo);

	// 농지대여 상세 정보
	public FarmLendVO myFarmLendInfo(String boardNo);

	// 농지대여 나의 신청 상세 정보
	public FarmLendApplyVO mySubInfo(String aplNo);

	// 농지대여 나의 신청 삭제
	public boolean deleteMyFarmSub(String aplNo);

	// 나의 경매장 등록 리스트
	public List<AuctionVO> myActionList(String memNo);

	// 나의 입찰 리스트
	public List<AuctionVO> myBidList(String memNo);

}
