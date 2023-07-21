package com.yedam.app.user.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class InquiryVO {
//	INQ_NO	문의 번호
//	MEM_NO	회원 번호
//	INQ_TITLE	문의 제목
//	INQ_CTG	문의 카테고리
//	INQ_DESCT	문의 내용
//	REPL_STTS	답변 여부
//	INQ_DATE	문의 일자
//	REPL_DESCT	답변 내용
//	REPL_DATE	답변 일자
	private String inqNo;
	private String memNo;
	private String inqTitle;
	private String inqCtg;
	private String inqDesct;
	private String replStts;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date inqDate;
	private String replDesct;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date replDate;
	
	//상세코드
	private String codeDesct; //문의 카테고리
	private String replCodeDesct; //답변여부
	
	//작성회원정보
	private String name;
}
