package com.yedam.app.market.mapper;

import java.util.List;

import com.yedam.app.market.vo.FundingVO;

public interface FundingMapper {
	//펀딩 리스트 가져오기
	public List<FundingVO> getFundingList();
	
	//펀딩 리스트 가져오기(페이징)
	public List<FundingVO> getFundingListPage(int page);
	
	//펀딩 총 갯수 가져오기
	public int fundingTotal();
	
	//단일 펀딩 정보 가져오기
	public FundingVO getFundingInfo(FundingVO vo);
}
