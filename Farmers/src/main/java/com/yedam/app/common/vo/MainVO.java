package com.yedam.app.common.vo;

import lombok.Data;

@Data
public class MainVO {

	// 추천작물 테이블
	//recom_crops
	private String dstCtg; //지역
	private String ssnCtg; //계절
	private String img; 
	private String mapNo;	
	//recom_crops_info
	private String desct; //설명
	
	
}
