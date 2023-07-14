package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.MarketVO;

public interface MarketService {
	
	public List<MarketVO> getMarketList();
	
	//단건조회
	public MarketVO getMarketInfo(String MktNo);
	
	//총 갯수
	public int selectCount();
	
	//등록
	public int insertMk(MarketVO vo);
	
	//수정
	public int updateMk();
		
	//삭제
	public int deleteMk();
	
	
	
}
