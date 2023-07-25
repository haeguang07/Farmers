package com.yedam.app.activity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ExpVO {
	private String boardNo;
	private String memNo;
	private String title;
	private String rep;
	private String detaDesct;
	private String zip;
	private String addr;
	private String dst1;
	private String dst2;
	private int pptPer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date exStrDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date exEndDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regDate;
	private String regStts;
	private double lati;
	private double longi;
	private String detaAddr;
}
