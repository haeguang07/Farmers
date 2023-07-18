package com.yedam.app.user.vo;

import java.util.Date;

import lombok.Data;

@Data
public class PointsVO {
//	PNT_NO	포인트 번호
//	MEM_NO	회원 번호
//	PNT	포인트
//	PNT_STTS	포인트 상태
//	PNT_RCRD	포인트 내역
//	LEFT_PNT	남은 포인트
//	USE_DATE	사용 일자

	private String pntNo;
	private String memNo;
	private int pnt;
	private String pntStts;
	private String pntRcrd;
	private int leftPnt;
	private Date useDate;
}
