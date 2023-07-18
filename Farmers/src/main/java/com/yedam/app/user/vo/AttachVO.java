package com.yedam.app.user.vo;

import java.util.Date;

import lombok.Data;

@Data
public class AttachVO {
//	ATCH_FILE_NO	첨부 파일 번호
//	MEM_NO	회원 번호
//	ATCH_FILE_NAME	첨부 파일 명
//	UPL_FILE_NAME	업로드 파일 명
//	UPL_DATE	업로드 일자
//	BOARD_NO	게시판 번호
//	ATCH_NO	첨부 번호
	
	private String atchFileNo;
	private String memNo;
	private String atchFileName;
	private String uplFileName;
	private Date uplDate;
	private String boardNo;
	private String atchNo;

}
