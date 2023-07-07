package com.yedam.app.market.vo;

import java.util.Date;

import lombok.Data;

@Data
public class AuctionVO {

	// table auction
	private String actNo;
	private String actTitle;
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
	
	// table auction_apply
	private String aplNo;
	private String memNo;
	private int bidPrice;
	private String sbidStts;
	
}