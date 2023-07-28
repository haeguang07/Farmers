package com.yedam.app.common.vo;

import lombok.Data;

@Data
public class WarnVO {

//	RPT_NO	신고 번호
//	MEM_NO	회원 번호
//	BOARD_NO	게시판 번호
//	PRC_STTS	처리 상태
//	RPT_RSN	신고 이유
//	BOARD_CTG	게시판 분류

	private String rptNo;
	private String memNo;
	private String boardNo;
	private String prcStts;
	private String rptRsn;
	private String boardCtg;


}
