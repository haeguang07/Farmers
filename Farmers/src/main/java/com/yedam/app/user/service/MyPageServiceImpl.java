package com.yedam.app.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.activity.vo.ExpApplyVO;
import com.yedam.app.activity.vo.ExpVO;
import com.yedam.app.activity.vo.SkilledVO;
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
import com.yedam.app.user.mapper.MyPageMapper;
import com.yedam.app.user.vo.AlertVO;
import com.yedam.app.user.vo.AttachVO;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.PointsVO;

@Service
public class MyPageServiceImpl implements MyPageService {
	@Autowired
	MyPageMapper myPageMapper;
	
	@Override
	public MemberVO checkPassword(String memNo) {
		return myPageMapper.checkPassword(memNo);
	}

	@Override
	public MemberVO getMemberInfo(String memNo) {
		return myPageMapper.getMemberInfo(memNo);
	}

	@Override
	public void modifyMember(MemberVO vo) {
		myPageMapper.modifyMember(vo);
	}

	@Override
	public List<PointsVO> pointHistory(String memNo) {
		return myPageMapper.pointHistory(memNo);
	}

	@Override
	public boolean upgradeGrade(AttachVO vo) {
		return myPageMapper.upgradeGrade(vo) >0;
	}

	@Override
	public boolean secession(String memNo) {
		return myPageMapper.secession(memNo) > 0;
	}

	@Override
	public PaymentVO myPayList(PaymentVO vo) {
		return myPageMapper.myPayList(vo);
	}

	@Override
	public List<PaymentVO> myPayNo(String memNo) {
		return myPageMapper.myPayNo(memNo);
	}
	

	@Override
	public boolean refund(String payNo) {
		return myPageMapper.refund(payNo) > 0;
	}

	@Override
	public List<InquiryVO> myInquiry(String memNo) {
		return myPageMapper.myInquiry(memNo);
	}

	@Override
	public boolean addInquiry(InquiryVO vo) {
		return myPageMapper.addInquiry(vo) > 0;
	}

	@Override
	public InquiryVO myInquiryInfo(String inqNo) {
		return myPageMapper.myInquiryInfo(inqNo);
	}

	@Override
	public List<AlertVO> alertList(String memNo) {
		return myPageMapper.alertList(memNo);
	}

	@Override
	public AlertVO alertInfo(String alrtNo) {
		return myPageMapper.alertInfo(alrtNo);
	}

	@Override
	public boolean updateAlrtStts(String alrtNo) {
		return myPageMapper.updateAlrtStts(alrtNo) > 0;
	}

	@Override
	public List<FarmLendVO> myFarmLendList(String memNo) {
		return myPageMapper.myFarmLendList(memNo);
	}

	@Override
	public List<FarmLendApplyVO> subFarmLendList(String memNo) {
		return myPageMapper.subFarmLendList(memNo);
	}

	@Override
	public FarmLendVO myFarmLendInfo(String boardNo) {
		return myPageMapper.myFarmLendInfo(boardNo);
	}

	@Override
	public List<FarmLendApplyVO> mySubInfo(String aplNo) {
		return myPageMapper.mySubInfo(aplNo);
	}

	@Override
	public boolean deleteMyFarmSub(String aplNo) {
		return myPageMapper.deleteMyFarmSub(aplNo) > 0;
	}

	@Override
	public List<AuctionVO> myActionList(String memNo) {
		return myPageMapper.myActionList(memNo);
	}

	@Override
	public List<AuctionVO> myBidList(String memNo) {
		return myPageMapper.myBidList(memNo);
	}

	@Override
	public List<AuctionApplyVO> myAuctionBidList(String boardNo) {
		return myPageMapper.myAuctionBidList(boardNo);
	}

	@Override
	public List<FundingVO> myFundingList(String memNo) {
		return myPageMapper.myFundingList(memNo);
	}

	@Override
	public List<PaymentDetailVO> myFundingPayList(String boardNo) {
		return myPageMapper.myFundingPayList(boardNo);
	}

	@Override
	public List<BnbVO> myBnbList(String memNo) {
		return myPageMapper.myBnbList(memNo);
	}

	@Override
	public List<PaymentDetailVO> myBnbRsvList(String baordNo) {
		return myPageMapper.myBnbRsvList(baordNo);
	}
	
	@Override
	public List<CropsSaleVO> myCropsSaleList(String memNo) {
		return myPageMapper.myCropsSaleList(memNo);
	}

	@Override
	public List<PaymentDetailVO> myCropsPayList(String boardNo) {
		return myPageMapper.myCropsPayList(boardNo);
	}

	@Override
	public List<MarketVO> myMarketList(String memNo) {
		return myPageMapper.myMarketList(memNo);
	}

	@Override
	public List<PaymentDetailVO> myMarketPayList(String boardNo) {
		return myPageMapper.myMarketPayList(boardNo);
	}

	@Override
	public List<BoardVO> myBoardList(String memNo) {
		return myPageMapper.myBoardList(memNo);
	}

	@Override
	public List<PaymentDetailVO> mySalesPayList(String memNo) {
		return myPageMapper.mySalesPayList(memNo);
	}

	@Override
	public PaymentDetailVO mySalesPayInfo(String payDetaNo) {
		return myPageMapper.mySalesPayInfo(payDetaNo);
	}

	@Override
	public void myFarmAplStts(FarmLendApplyVO vo) {
		myPageMapper.myFarmAplStts(vo);;
	}

	@Override
	public boolean updateApplyStts(String aplNo) {
		return myPageMapper.updateApplyStts(aplNo) > 0;
	}

	@Override
	public boolean updateShipStts(PaymentDetailVO vo) {
		return myPageMapper.updateShipStts(vo) > 0;
	}

	@Override
	public void refundProcedure(PaymentDetailVO vo) {
		myPageMapper.refundProcedure(vo);
	}

	@Override
	public List<ExpVO> myExpList(String memNo) {
		return myPageMapper.myExpList(memNo);
	}

	@Override
	public List<ExpApplyVO> myExpSubList(String memNo) {
		return myPageMapper.myExpSubList(memNo);
	}

	@Override
	public List<ExpApplyVO> myExpSubPeoList(String aplNo) {
		return myPageMapper.myExpSubPeoList(aplNo);
	}

	@Override
	public ExpApplyVO myExpSubInfo(String aplNo) {
		return myPageMapper.myExpSubInfo(aplNo);
	}

	@Override
	public int deleteExpApply(String aplNo) {
		return myPageMapper.deleteExpApply(aplNo);
	}

	@Override
	public void myExpAplStts(ExpApplyVO vo) {
		myPageMapper.myExpAplStts(vo);
		
	}

	@Override
	public int updateExpApplyStts(String aplNo) {
		return myPageMapper.updateExpApplyStts(aplNo);
	}

	@Override
	public List<SkilledVO> mySkilledList(SkilledVO vo) {
		return myPageMapper.mySkilledList(vo);
	}

	@Override
	public List<SkilledVO> mySkilledSubList(String memNo) {
		return myPageMapper.mySkilledSubList(memNo);
	}
	
	

}
