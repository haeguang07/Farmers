package com.yedam.app.market.mapper;

import java.util.List;

import com.yedam.app.market.vo.AuctionVO;

public interface AuctionMapper {

	// 경매 리스트 조회
	public List<AuctionVO> showAuctionList();	
	
	// 경매 리스트 조회(페이징)
	public List<AuctionVO> getAuctionListPage(int page);
	
	// 단건 경매 정보 조회
	public AuctionVO selectAuction(int actNo);
	
	// 입찰 최고가 조회
	public AuctionVO calHighestBid(int actNo);
	
	// 경매 등록
	public int addAuction(AuctionVO vo);
	
	// 경매 임시 저장
	public int tempAuction(AuctionVO vo);
	
	// 경매 수정
	public int updateAuction(AuctionVO vo);
}
