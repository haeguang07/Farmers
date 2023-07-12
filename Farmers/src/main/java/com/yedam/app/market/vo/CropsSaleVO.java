package com.yedam.app.market.vo;

//import java.util.Date;

//import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CropsSaleVO {
	// crops_sale
	public String crpSaleNo;
	public String memNo;
	public String crpTitle;
	public String crpCtg;
	public int price;
	public int qty;
	public String rep;
	public String detaDesct;
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	public String regDate;
	public String regStts;
	
	// review
	public String revNo;
	public int rate;
	public String boardNo;
}
