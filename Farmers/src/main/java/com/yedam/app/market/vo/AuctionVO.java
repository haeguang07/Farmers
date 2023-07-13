package com.yedam.app.market.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AuctionVO {

	// 경매
	private String actNo;
	private String actTitle;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date actDate;
	private String desct;
	private int saleQty;
	private int bnp;
	private String actTrm ;
	private int shipPrice;
	private int shipDurAvg;
	private int shipDurMax;
	private String rep;
	private String regStts;
	private String atchNo;
	
	// 카운트 다운
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date actExpireDate;
	
	// 경매 신청
	private String aplNo;
	private String memNo;
	private int bidPrice;
	private String sbidStts;
	
	// 멤버
	private String nick;
	
}
