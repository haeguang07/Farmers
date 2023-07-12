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
	
	//join 데이터
	private int price;
}
