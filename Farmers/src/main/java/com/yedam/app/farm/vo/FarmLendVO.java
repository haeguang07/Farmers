package com.yedam.app.farm.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yedam.app.user.vo.AttachVO;

import lombok.Data;

@Data
public class FarmLendVO {
	// farm_lend
	private String boardNo;
	private String zip;
	private String addr;
	private String detaAddr;
	private int area;
	private String desct;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date lendStrDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date lendEndDate;
	private int lendPrice;
	private String atchNo;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date regDate;
	private String regStts;
	private String memNo;
	private String dst1;
	private String dst2;
	private double lati;
	private double longi;
	
	// attachment (파일목록)
	private List<AttachVO> files;
	
	// main_crops (주요 재배 작물 배열)
	private String mcrp;
	private String[] mcrpList;
	
	// member
	private String nick;
	
	// farm_lend_apply (신청목록)
	private List<FarmLendApplyVO> applys;
	
	//상세코드
	private String codeDesct;

}
