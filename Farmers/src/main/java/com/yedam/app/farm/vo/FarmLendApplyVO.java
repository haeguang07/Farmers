package com.yedam.app.farm.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
<<<<<<< HEAD
import com.yedam.app.common.vo.AttachmentVO;
=======
import com.yedam.app.user.vo.AttachVO;
>>>>>>> branch 'main' of https://github.com/haeguang07/Farmers.git

import lombok.Data;

@Data
public class FarmLendApplyVO {

	private String aplNo;
	private String memNo;
	private String aplStts;
	private String boardNo;
	private String atchNo;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date aplDate;
	private int lendPrice;
	private String atchFileName;
	private String uplFileName;
	
	//상세코드
	private String codeDesct;
	
	//대여일수
	private int allPrice;
	
	//주소
	private String addr;
	private String detaAddr;
	
	//작성자
	private String name;
	private String mbl;
	

	private List<AttachmentVO> attachList;

	// attachment (파일목록)
	private List<AttachVO> files;
}
