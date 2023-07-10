package com.yedam.app.market.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FundingVO {
	private String fndNo;
	private String memNo;
	private String fndTitle;
	private String desct;
	private Date fndStrDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date fndEndDate;
	private int goalPrice;
	private int fndPrice;
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
}
