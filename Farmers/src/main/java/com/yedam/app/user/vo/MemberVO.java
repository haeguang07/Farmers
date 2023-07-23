package com.yedam.app.user.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberVO implements Serializable {

	private String memNo;
	private String id;
	private String pw;
	private String email;
	private String nick;
	private String loginPath;
	private int rptCnt;
	private String memGrd;
	
	//Detail
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
	@DateTimeFormat(pattern="yyyy-MM-dd")
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
