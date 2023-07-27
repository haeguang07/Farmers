package com.yedam.app.user.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.activity.vo.ExpVO;
import com.yedam.app.common.vo.SearchVO;
import com.yedam.app.common.vo.WarnVO;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.AuctionVO;
import com.yedam.app.market.vo.FundingVO;
import com.yedam.app.market.vo.MarketVO;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.UpdateSttsVO;

public interface AdminMapper {
	//전체회원 조회
	public List<MemberVO> selectMemberList(SearchVO vo);
	//회원 상태 수정
	public void updateMember(List<MemberVO> list, Map<String, Object> map);
	
	//문의 조회
	public List<InquiryVO> selectInqueryList(SearchVO vo);
	//문의 답변
	public int updateInquiry(InquiryVO vo);
	
	//농지대여 조회
	public List<FarmLendVO> selectFarmLendList();
	//마켓 조회
	public List<MarketVO> selectMarketList();
	//비엔비 조회
	public List<BnbVO> selectFarmBnbList();
	//경매장 조회
	public List<AuctionVO> selectAuctionList();
	//펀딩 조회
	public List<FundingVO> selectFundingList();
	//체험하기 조회
	public List<ExpVO> selectExpList();
	//신고 조회
	public List<WarnVO> selectWarnList();
	
	//동적으로 승인/거부
	public int updateStatus(UpdateSttsVO VO);
	
}
