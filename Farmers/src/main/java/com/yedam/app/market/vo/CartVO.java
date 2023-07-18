package com.yedam.app.market.vo;

import lombok.Data;

@Data
public class CartVO {
	//cart 데이터
	private String cartNo;
	private String boardNo;
	private int qty;
	private String memNo;
	private String boardCtg;
	private String codeDesct; // 상세코드로 테이블명 조회
	
	//join 데이터
	private int price;
	private String title;
	private String crpTitle; //농작물
	private String eqpTitle; //농기구
	private String mktTitle;  //전원마켓
}
