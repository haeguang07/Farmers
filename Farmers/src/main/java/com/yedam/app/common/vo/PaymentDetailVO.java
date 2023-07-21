package com.yedam.app.common.vo;

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
	
	//board table
	private String title;
	private String rep;
}
