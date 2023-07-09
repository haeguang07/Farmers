package com.yedam.app.activity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ExpVO {
	private String frexNo;
	private String memNo;
	private String frexTitle;
	private String rep;
	private String detaDesct;
	private int zip;
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
	private String lagt;
}
