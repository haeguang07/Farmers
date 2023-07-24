package com.yedam.app.market.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CropsSaleVO {
	// crops_sale
	private String boardNo;
	private String memNo;
	private String title;
	private String crpCtg;
	private int price;
	private int qty;
	private String rep;
	private String detaDesct;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date regDate;
	private String regStts;
	
	// review
	private String revNo;
	private int rate;
	
	// member
	private String nick;
	
	// member_detail
	private String memGrd;
}
