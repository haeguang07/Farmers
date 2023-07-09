package com.yedam.app.farm.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FarmLendVO {
	public String frldLendNo;
	public String memNo;
	public int zip;
	public String addr;
	public String detaAddr;
	public int area;
	public String desct;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date lendStrDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date lendEndDate;
	public int lendPrice;
	public String atchNo;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date regDate;
	public String regStts;
	public String lagt;
}
