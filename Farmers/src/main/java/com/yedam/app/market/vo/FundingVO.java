package com.yedam.app.market.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FundingVO {
	private String boardNo;
	private String memNo;
	private String title;
	private String desct;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date fndStrDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date fndEndDate;
	private int goalPrice;
	private int fndPrice;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date shipStrDate;
	private String rep;
	private String regStts;
	private String crpCtg;
	private String fndStts;
	private int dday;
	private int priceSum;
	private int payCount;
	private String nick;
	private float goalPer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date regDate;
}
