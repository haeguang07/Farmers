package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.AuctionVO;

public interface AuctionService {
		// 전체 조회
		public List<AuctionVO> getAuctionList();	
		
		// 단건 조회
		public AuctionVO getOneAuction(int actNo);
		
		// 입찰 최고가 조회
		public AuctionVO calHighestBid(int actNo);
		
		// 등록
		public boolean InsertAuction(AuctionVO vo);
		
		// 수정
		public boolean updateAuction(AuctionVO vo);
		
		// 삭제
		public boolean deleteAuction(int ActNo);
}
