package com.yedam.app.market.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CropsSaleVO {
	// crops_sale
	public String boardNo;
	public String memNo;
	public String title;
	public String crpCtg;
	public int price;
	public int qty;
	public String rep;
	public String detaDesct;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@JsonFormat(pattern="yyyy/MM/dd")
	public Date regDate;
	public String regStts;
	
	// review
	public String revNo;
	public int rate;
	
	// member
	public String nick;
	public String memGrd;
}
