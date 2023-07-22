package com.yedam.app.market.vo;

import lombok.Data;

@Data
public class AuctionApplyVO {
//	APL_NO	신청 번호
//	MEM_NO	회원 번호
//	BOARD_NO	경매 번호
//	PRICE	입찰 금액
//	SBID_STTS	낙찰 여부
	
	private String aplNo;
	private String memNo;
	private String boardNo;
	private int price;
	private String sbidStts;
	
	//경매 정보
	private String title;
	
	//입찰자 정보
	private String nick;
}
