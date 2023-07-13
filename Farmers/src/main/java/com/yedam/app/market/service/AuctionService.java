package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.AuctionVO;

public interface AuctionService {
		// 전체 조회 (페이징)
		public List<AuctionVO> getAuctionList(int page, String order);	
		
		// 경매 리스트 합계
		public int auctionTotal();
		
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
