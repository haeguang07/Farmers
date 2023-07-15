package com.yedam.app.common.vo;

import lombok.Data;

@Data
public class PayProductVO {
	private String boardNo;
	private int qty;
	private String boardCtg;
	//private String codeDesct; 테이블명
	
	private String title;
	private int price;
	private String rep;
	
	//기간으로 결제하는 것들 필드 생성
}
