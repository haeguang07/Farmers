package com.yedam.app.common.vo;

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
}
