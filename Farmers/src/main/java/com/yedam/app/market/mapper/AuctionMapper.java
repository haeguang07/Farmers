package com.yedam.app.market.mapper;

import java.util.List;

import com.yedam.app.market.vo.AuctionVO;

public interface AuctionMapper {

	// 전체 조회
	public List<AuctionVO> showAllList();	
	
	// 단건 조회
	public AuctionVO selectAuction(int ActNo);
	
	// 등록
	public int addAuction(AuctionVO vo);
	
	// 임시 저장
	public int tempAuction(AuctionVO vo);
	
	// 수정
	public int updateAuction(AuctionVO vo);
}
