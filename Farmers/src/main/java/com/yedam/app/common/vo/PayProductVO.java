package com.yedam.app.common.vo;

import java.util.Date;

import lombok.Data;

@Data
public class PayProductVO {
	private String boardNo;
	private int qty;
	private String boardCtg;
	private String payNo;
	private String memNo;
	private Date rsvStrDate;
	private Date rsvEndDate;
	//private String codeDesct; 테이블명
	
	private String title;
	private int price;
	private String rep;
	
	//기간으로 결제하는 것들 필드 생성
}
