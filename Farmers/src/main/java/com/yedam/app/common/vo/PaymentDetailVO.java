package com.yedam.app.common.vo;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentDetailVO {
	//payment_detail
	private String payDetaNo;
	private String boardNo;
	private String payNo;
	private int qty;
	private int price;
	private String shipStts;
	private String boardCtg;
	private Date rsvStrDate;
	private Date rsvEndDate;
 	
	//board table
	private String title;
	private String rep;
	
	//payTable
	private Date payDate;
	private String payCode;
	private String rcvAddr;
	private String rcvDetaAddr;
	private String zip;
	
	//가공 데이터
	private int totalPrice;
	private String nick; //구매자 닉네임
	private String mbl; //구매자 전화번호
	private String memNo; //구매자 번호
	private String boardCtgCode;
	
	//경매
	private String aplNo;
}
