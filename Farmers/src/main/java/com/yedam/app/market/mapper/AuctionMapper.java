package com.yedam.app.market.mapper;

import java.util.List;

import com.yedam.app.market.vo.AuctionVO;

public interface AuctionMapper {

	// 전체 조회
	public List<AuctionVO> showAll();	
	
	// 단건 조회
	public AuctionVO selActionL(int ActNo);
	
	// 등록
	public int addAct(AuctionVO vo);
	
	// 수정
	public int updateAct(AuctionVO vo);
	
	// 삭제
	public int deledeAct(int i);
}
