package com.yedam.app.user.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class AdminSearchVO {
	private String id;
	private String nick;
	private String login;
	private String grd;
	private String stts;
	private String ctg;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date str;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date end;
}
