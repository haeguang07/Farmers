package com.yedam.app.board.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BoardVO {
	
	private String boardNo;
	private String memNo;
	private String postCtg;
	private String postCtgName;
	private String dst1;
	private String dst2;
	private String title;
	private String img;
	private String desct;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date wrtDate;
	private int hitCount;
	
	private String nick;
	
	private int recomCount;
	
	private int pageNum;
	
	List<ReplyVO> replys;
	List<RecomVO> recoms;
	
}	
