package com.yedam.app.activity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BnbVO {

	// farmbnb
	private String boardNo;
	private String memNo;
	private String title;
	private String dst1;
	private String zip;
	private String addr;
	private String addrDetail;
	private String price;
	private String rep;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date lendStrDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date lendEndDate;
	private String detaDesct;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date regDate;
	private String regStts;
	private String lendStts;
	private double lati;
	private double longi;

	// farmbnb_apply
	private String aplNo;
	private String aplStts;
	private Date rsvStrDate;
	private Date rsvEndDate;
	private Date aplDate;
	private int lendTotal;

	// member, detail
	private String id;
	private String email;
	private String nick;
	private String name;
	private String memAdr;
	private String memAdrdeta;
	private String mbl;

	// review
	private int count;
	private int round;
	

	// cart 데이터
	private String cartNo;
	private int qty;
	private String boardCtg;
	private String codeDesct; // 상세코드로 테이블명 조회

}
