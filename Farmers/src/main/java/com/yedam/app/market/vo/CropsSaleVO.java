package com.yedam.app.market.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CropsSaleVO {
	public String crpSaleNo;
	public String memNo;
	public String crpTitle;
	public String crpCtg;
	public int price;
	public int qty;
	public String rep;
	public String dataDesct;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date regDate;
	public String regStts;
}
