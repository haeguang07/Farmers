package com.yedam.app.activity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SkilledVO {

	private String boardNo;
	private String memNo;
	private String dst1;
	private String dst2;
	private String div;
	private String atchNo;
	private String title;
	private String desct;
	private String dayDiv;
	private String wageDiv;
	private int price;
	private String cndt;
	private String addrDesct;
	private String stts;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date regDate;
	
}
