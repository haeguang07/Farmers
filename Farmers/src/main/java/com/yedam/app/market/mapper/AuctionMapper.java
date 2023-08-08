package com.yedam.app.market.mapper;

import java.util.List;

import com.yedam.app.market.vo.AuctionVO;

public interface AuctionMapper {
	
	// 경매 리스트 조회(페이징)
	public List<AuctionVO> getAuctionListPage(int page, String order);
	
	// 경매 리스트 합계
	public int auctionTotal();
	
	// 다음 경매 리스트
	public List<AuctionVO> getNextAuctionList();
	
	// 단건 경매 정보 조회
	public AuctionVO selectAuction(String boardNo);
	
	// 입찰하기
	public int bidAuction(AuctionVO vo);
	
	// 경매 등록
	public int addAuction(AuctionVO vo);
	
	// 경매 임시 저장
	public int tempAuction(AuctionVO vo);
	
	// 경매 수정
	public int updateAuction(AuctionVO vo);
	
	//상태
	public int updateAuctionReg();
	
	// 즉시 구매 프로시저 (알람 및 상태 변경)
	public void buyAuctionAlert(AuctionVO vo);
	
	// 입찰 프로시저 (알람 및 상태 변경)
	public void bidAuctionAlert(AuctionVO vo);
	
	// 경매종료일 스케줄러
	public void expiredAuction();
	
	// 경매 종료 후 24시간내 미결제 시 스케줄러
	public void expiredAuctionPay();
	
	// 판매자 정보
	public AuctionVO sellerInfo(String boardNo);
	
}
