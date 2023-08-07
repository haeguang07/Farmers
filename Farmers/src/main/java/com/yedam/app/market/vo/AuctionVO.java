package com.yedam.app.market.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AuctionVO {

	// 경매
	private String boardNo;
	private String title;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
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
	private String bidCount;
	
	// 카운트 다운
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date actExpireDate;
	
	// 경매 신청
	private String aplNo;
	private String memNo;
	private int price;
	private String sbidStts;
	
	// 멤버
	private String id;
	private String email;
	private String nick;
	private String name;
	private String zip;
	private String memZip;
	private String memAdr;
	private String memAdrdeta;
	private String mbl;
	
}
