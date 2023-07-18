package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.MarketVO;

public interface MarketService {

	// 전체조회
	public List<MarketVO> getMarketList(String mktCtg, int page, String order);

	// 단건조회
	public MarketVO getMarketInfo(String boardNo);

	// 총 갯수
	public int selectCount(String mktCtg);

	// 등록
	public int insertMk(MarketVO vo);

	// 수정
	public int updateMk();

	// 삭제
	public int deleteMk();
}
