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
	public String boardNo;
	public String zip;
	public String addr;
	public String detaAddr;
	public int area;
	public String desct;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date lendStrDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date lendEndDate;
	public int lendPrice;
	public String atchNo;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date regDate;
	public String regStts;
	public String memNo;
	public String dst1;
	public String dst2;
	public double lati;
	public double longi;
	
	// attachment (파일목록)
	public List<AttachVO> files;
	
	// main_crops (주요 재배 작물 배열)
	public String mcrp;
	public String[] mcrpList;
	
	// member
	public String nick;
	
	// farm_lend_apply (신청목록)
	private List<FarmLendApplyVO> applys;
	
	//상세코드
	private String codeDesct;

}
