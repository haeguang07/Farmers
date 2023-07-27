package com.yedam.app.activity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ExpApplyVO {
	private String aplNo;
	private String memNo;
	private String aplStts;
	private String boardNo;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date aplDate;
	private int aplPer;
	
	//experience 데이터
	private String title;
	private String rep;
	private String regStts;
	
	//신청자 정보
	private String nick;
	private String mbl;
}
