package com.yedam.app.activity.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BnbVO {
	
	//	farmbnb	
	private String bnbNo;
	private String memNo;
	private String bnbTitle;
	private String dst1;
	private String zip;
	private String addr;
	private String price;
	private String rep;
	private Date lendStrDate;
	private Date lendEndDate;
	private String detaDesct;
	private Date regDate;
	private String regStts;
	private String lendStts;

	//	farmbnb_apply
	private String aplNo;
	private String aplStts;
	private Date rsvStrDate;
	private Date rsvEndDate;
	private Date aplDate;
	private int lendTotal;
	
	

}
