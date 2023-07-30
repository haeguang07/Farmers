package com.yedam.app.user.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AlertVO {
//	ALRT_NO	알림 번호
//	MEM_NO	회원 번호
//	ALRT_STTS	알림 상태
//	ALRT_TITLE	알림 제목
//	ALRT_DESCT	알림 내용
//	BOARD_CTG	게시판 카테고리
//	ALRT_DATE	알림 일자
	
	private String alrtNo;
	private String memNo;
	private String alrtStts;
	private String alrtTitle;
	private String alrtDesct;
	private String boardCtg;
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date alrtDate;
	
}
