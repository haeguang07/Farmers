package com.yedam.app.activity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SkilledVO {

	// 금손 테이블
	private String boardNo;
	private String memNo;
	private String dst1;
	private String dst2;
	private String dst1Name;
	private String div;
	private String img;
	private String title;
	private String desct;
	private String dayDiv;
	private String wageDiv;
	private int price;
	private String cndt;
	private String addrDesct;
	private String stts;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date regDate;
	
	// 금손 신청 테이블
	private String aplNo;
	private String aplMsg;
	private String aplStts;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
	private String aplDate;
	

	// 추가
	private String nick;
	private String search;
	

	//신청자 정보
	private String mbl;

}
