package com.yedam.app.farm.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FarmLendApplyVO {

	private String aplNo;
	private String memNo;
	private String aplStts;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date rsvStrDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date rsvEndDate;
	private String frldLendNo;
	private String archNo;
	private int lendTotal;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date aplDate;
	
	//상세코드
	private String codeDesct;
	
	//대여일수
	private int allPrice;
	
	//주소
	private String addr;
	private String detaAddr;
	
	//작성자
	private String name;
	private String mbl;
}
