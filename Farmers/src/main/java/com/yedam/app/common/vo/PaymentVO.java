package com.yedam.app.common.vo;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentVO {

	private String payNo;
	private String memNo;
	private String rcvName;
	private String rcvMbl;
	private String rcvAddr;
	private String rcvDetaAddr;
	private int usePnt;
	private String payMthd;
	private int totalPayPrice;
	private String payCode;
	private Date payDate;
	
	//결제내역조회
	private String boardCtg;
	private String boardNo;
	private String title;
	private String rep;
	private int count;

}
