package com.yedam.app.user.service;

import java.util.List;
import java.util.Map;

import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.activity.vo.ExpVO;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.AuctionVO;
import com.yedam.app.market.vo.FundingVO;
import com.yedam.app.market.vo.MarketVO;
import com.yedam.app.user.vo.AdminSearchVO;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.UpdateSttsVO;

public interface AdminService {
	//전체조회
	public List<MemberVO> getMemberList(AdminSearchVO searchVO);
	//회원 상태 수정
	public List<MemberVO> modifyMemberStts(List<MemberVO> list);
	//문의 조회
	public List<InquiryVO> getInqueryList(AdminSearchVO searchVO);
	//문의 답변
	public boolean replyInquiry(InquiryVO vo);
	//농지대여 조회
	public List<FarmLendVO> getFarmLendList(AdminSearchVO searchVO);
	//체험하기 조회
	public List<ExpVO> getExpList(AdminSearchVO searchVO);
	//마켓 조회
	public List<MarketVO> getMarketList(AdminSearchVO searchVO);
	//비엔비 조회
	public List<BnbVO> getFarmBnbList(AdminSearchVO searchVO);
	//경매장 조회
	public List<AuctionVO> getAuctionList(AdminSearchVO searchVO);
	//펀딩 조회
	public List<FundingVO> getFundingList(AdminSearchVO searchVO);

	
	//동적으로 승인/거부
	public Map<String,Object> chageStatus(List<UpdateSttsVO> list);
	

}
