package com.yedam.app.market.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yedam.app.common.vo.ReviewVO;

import lombok.Data;

@Data
public class MarketVO {

//	MKT_NO	전원마켓 번호
//	MEM_NO	회원 번호
//	MKT_CTG	전원마켓 카테고리
//	MKT_TITLE	전원마켓 제목
//	PRICE	금액
//	QTY	수량
//	REP	대표사진
//	DETA_DESCT	상세 설명
//	REG_DATE	등록 일자
//	REG_STTS	등록 상태

	private String boardNo;
	private String memNo;
	private String mktCtg;
	private String title;
	private String price;
	private String qty;
	private String rep;
	private String detaDesct;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date regDate;
	private String regStts;

	// member, detail
	private String id;
	private String email;
	private String nick;
	private String name;
	private String memAdr;
	private String memAdrdeta;
	private String mbl;
	private String zip;

	// review
	private int count;
	private int round;
	private List<ReviewVO> reviews;

}
