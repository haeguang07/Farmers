package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.AuctionVO;

public interface AuctionService {
		// 전체 조회
		public List<AuctionVO> getAllAuctions();	
		
		// 단건 조회
		public AuctionVO getOneAuction(int ActNo);
		
		// 등록
		public boolean InsertAuction(AuctionVO vo);
		
		// 수정
		public boolean updateAuction(AuctionVO vo);
		
		// 삭제
		public boolean deleteAuction(int ActNo);
}
