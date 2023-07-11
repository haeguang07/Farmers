package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.FundingVO;

public interface FundingService {
	// 펀딩 리스트 가져오기
	public List<FundingVO> getFundingList();

	// 펀딩 리스트 가져오기(페이징)
	public List<FundingVO> getFundingListPage(int page, String category, String order, String search);

	// 펀딩 총 갯수 가져오기
	public int fundingTotal(String category, String search);

	// 단일 펀딩 정보 가져오기
	public FundingVO getFundingInfo(FundingVO vo);

	// 추천 펀딩 정보 가져오기
	public List<FundingVO> getPolpularFnd();
	
	//펀딩 등록
	public boolean insertFunding(FundingVO vo);
}
