package com.yedam.app.farm.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FarmLendVO {
	// farm_lend
	public String boardNo;
	public int zip;
	public String addr;
	public String detaAddr;
	public int area;
	public String desct;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@JsonFormat(pattern="yyyy/MM/dd")
	public Date lendStrDate;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@JsonFormat(pattern="yyyy/MM/dd")
	public Date lendEndDate;
	public int lendPrice;
	public String atchNo;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@JsonFormat(pattern="yyyy/MM/dd")
	public Date regDate;
	public String regStts;
	public String memNo;
	public String dst1;
	public String dst2;
	public double lati;
	public double longi;
	
	// main_crops
	public String mcrp;
	
	// member
	public String nick;
	
	//신청목록
	private List<FarmLendApplyVO> applys;
}
