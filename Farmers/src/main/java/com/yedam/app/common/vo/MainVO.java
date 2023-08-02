package com.yedam.app.common.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MainVO {

	// 추천작물 테이블
	// recom_crops
	private String dstCtg; // 지역
	private String ssnCtg; // 계절
	private String img;
	private String mapNo;
	// recom_crops_info
	private String desct; // 설명

//	ALRT_NO
//	MEM_NO
//	ALRT_STTS
//	ALRT_TITLE
//	ALRT_DESCT
//	BOARD_CTG
//	ALRT_DATE

	private String alrtNo;
	private String memNo;
	private String alrtStts;
	private String alrtTitle;
	private String alrtDesct;
	private String boardCtg;
	private Date alrtDate;

}
