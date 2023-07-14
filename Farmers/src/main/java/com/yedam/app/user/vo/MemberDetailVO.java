package com.yedam.app.user.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class MemberDetailVO {
	private String memNo;
	private String name;
	private int	zip;
	private String addr;
	private String detaAddr;
	private String mbl;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date bDate;
	private String gen;
	private int pnt;
	private String stts;
	@DateTimeFormat(pattern="yyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date signDate;
	private String prof;
	private String grdAtchFile;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date wdrAplDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date blckDate;
}
