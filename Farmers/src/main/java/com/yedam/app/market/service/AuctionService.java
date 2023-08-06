package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.AuctionVO;

public interface AuctionService {
		// 전체 조회 (페이징)
		public List<AuctionVO> getAuctionList(int page, String order);	
		
		// 경매 리스트 합계
		public int auctionTotal();
		
		// 다음 경매 리스트
		public List<AuctionVO> getNextAuctionList();
		
		// 단건 경매 정보 조회
		public AuctionVO getAuctionInfo(String boardNo);
		
		// 입찰하기
		public boolean bidAuction(AuctionVO vo);
		
		// 등록
		public boolean InsertAuction(AuctionVO vo);
		
		// 수정
		public boolean updateAuction(AuctionVO vo);
		
		// 삭제
		public boolean deleteAuction(String boardNo);
		
		// 상태
		public boolean updateAuctionReg();
		
		// 즉시 구매 프로시저
		public void buyAuctionAlert(AuctionVO vo);
		
		// 입찰 프로시저 (알람 및 상태 변경)
		public void bidAuctionAlert(AuctionVO vo);
		
		// 경매종료일 스케줄러
		public void expiredAuction();
}
 