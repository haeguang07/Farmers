package com.yedam.app.activity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BnbVO {

	// farmbnb
	private String boardNo;
	private String memNo;
	private String title;
	private String dst1;
	private String zip;
	private String addr;
	private String addrDetail;
	private String price;
	private String rep;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lendStrDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lendEndDate;
	private String detaDesct;
	private Date regDate;
	private String regStts;
	private String lendStts;

	// farmbnb_apply
	private String aplNo;
	private String aplStts;
	private Date rsvStrDate;
	private Date rsvEndDate;
	private Date aplDate;
	private int lendTotal;

}
