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
	
	//가공 데이터
	private int totalPrice;
	private String nick; //구매자 닉네임
}
