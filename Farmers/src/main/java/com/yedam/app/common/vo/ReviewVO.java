package com.yedam.app.common.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {

//	REV_NO	리뷰 번호
//	MEM_NO	회원 번호
//	RATE	별점
//	REV_DESCT	리뷰 내용
//	WRT_DATE	작성 일자
//	BOARD_NO	게시판 번호
//	IMG	이미지

	private String revNo;
	private String memNo;
	private int rate;
	private String revDesct;
	private Date wrtDate;
	private String boardNo;
	private String img;

}
