package com.yedam.app.user.mapper;

import java.util.List;

import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.board.vo.BoardVO;
import com.yedam.app.common.vo.PaymentDetailVO;
import com.yedam.app.common.vo.PaymentVO;
import com.yedam.app.farm.vo.FarmLendApplyVO;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.AuctionApplyVO;
import com.yedam.app.market.vo.AuctionVO;
import com.yedam.app.market.vo.CropsSaleVO;
import com.yedam.app.market.vo.FundingVO;
import com.yedam.app.market.vo.MarketVO;
import com.yedam.app.user.vo.AlertVO;
import com.yedam.app.user.vo.AttachVO;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.PointsVO;

public interface MyPageMapper {
	// 비밀번호 확인
	public MemberVO checkPassword(String memNo);

	// 회원정보 가져오기
	public MemberVO getMemberInfo(String memNo);

	// 회원정보수정
	public void modifyMember(MemberVO vo);

	// 등업신청
	public int upgradeGrade(AttachVO vo);

	// 포인트 내역
	public List<PointsVO> pointHistory(String memNo);

	// 회원탈퇴
	public int secession(String memNo);

	// 결제번호 조회
	public List<PaymentVO> myPayNo(String memNo);

	// 결제내역 조회
	public PaymentVO myPayList(PaymentVO vo);
	
	//결제환불
	public int refund(String payNo);

	// 문의내역 조회
	public List<InquiryVO> myInquiry(String memNo);

	// 문의 등록
	public int addInquiry(InquiryVO vo);

	// 문의 상세 정보
	public InquiryVO myInquiryInfo(String inqNo);

	// 알림 리스트
	public List<AlertVO> alertList(String memNo);

	// 알림 상세
	public AlertVO alertInfo(String alrtNo);
	
	//알림 열람 변경
	public int updateAlrtStts(String alrtNo);
	
	//농지대여 등록 리스트 
	public List<FarmLendVO> myFarmLendList(String memNo);
	
	//농지대여 신청 리스트
	public List<FarmLendApplyVO> subFarmLendList(String memNo);
	
	//농지대여 상세 정보
	public FarmLendVO myFarmLendInfo(String boardNo);
	
	//농지대여 나의 신청 상세 정보
	public List<FarmLendApplyVO> mySubInfo(String aplNo);
	
	//농지대여 나의 신청 삭제
	public int deleteMyFarmSub(String aplNo);
	
	//나의 농지대여 신청 수락 및 전체 거절
	public void myFarmAplStts (FarmLendApplyVO vo);
	
	//농지대여 신청 거절
	public int updateApplyStts (String aplNo);
	
	//나의 경매장 등록 리스트
	public List<AuctionVO> myActionList(String memNo);
	
	//나의 입찰 리스트
	public List<AuctionVO> myBidList(String memNo);
	
	//내 경매의 입찰 리스트
	public List<AuctionApplyVO> myAuctionBidList(String boardNo);
	
	//나의 펀딩 리스트
	public List<FundingVO> myFundingList(String memNo);
	
	//나의 펀딩 구매자 리스트
	public List<PaymentDetailVO> myFundingPayList(String boardNo);
	
	//나의 Bnb 리스트
	public List<BnbVO> myBnbList (String memNo);
	
	//나의 Bnb 예약자 리스트
	public List<PaymentDetailVO> myBnbRsvList(String baordNo);
	
	//나의 농작물 판매 리스트
	public List<CropsSaleVO> myCropsSaleList (String memNo);
	
	//나의 농작물 구매자 리스트
	public List<PaymentDetailVO> myCropsPayList(String boardNo);

	//나의 전원마켓 리스트
	public List<MarketVO> myMarketList(String memNo);
	
	//나의 전원마켓 구매자 리스트
	public List<PaymentDetailVO> myMarketPayList(String boardNo);
	
	//나의 게시글 리스트
	public List<BoardVO> myBoardList (String memNo);
	
	//나의 판매상품의 결제 리스트
	public List<PaymentDetailVO> mySalesPayList(String memNo);
	
	//나의 판매상품의 결제 정보
	public PaymentDetailVO mySalesPayInfo (String payDetaNo);
	
	//나의 판매상품의 배송 상태 변경
	public int updateShipStts(PaymentDetailVO vo);

}
