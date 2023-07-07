package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.FundingVO;

public interface FundingService {
	//펀딩 리스트 가져오기
	public List<FundingVO> getFundingList();
	
	//단일 펀딩 정보 가져오기
	public FundingVO getFundingInfo(FundingVO vo);
}
