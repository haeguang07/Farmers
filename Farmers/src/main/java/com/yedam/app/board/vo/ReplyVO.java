package com.yedam.app.board.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReplyVO {

	private String cmtNo;
	private String memNo;
	private String cmtDesct;
	private String boardNo;
	private int	rcmNum;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date wrtDate;
	
	private String nick;
	
}
