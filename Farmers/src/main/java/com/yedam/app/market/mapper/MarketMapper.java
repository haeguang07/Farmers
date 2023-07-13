package com.yedam.app.market.mapper;

import java.util.List;

import com.yedam.app.market.vo.MarketVO;

public interface MarketMapper {
	
	//전체조회
	public List<MarketVO> getMarketList();
	
	
}
