package com.yedam.app.activity.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ExpVO {
	private String boardNo;
	private String memNo;
	private String title;
	private String rep;
	private String detaDesct;
	private String zip;
	private String addr;
	private String dst1;
	private String dst2;
	private int pptPer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date exStrDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date exEndDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date regDate;
	private String regStts;
	private double lati;
	private double longi;
	private String detaAddr;
	private int page;
	// member
	private String nick;
	
	//신청내역
	private List<ExpApplyVO> expApplyList;
}
