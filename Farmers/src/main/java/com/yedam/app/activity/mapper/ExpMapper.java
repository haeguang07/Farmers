package com.yedam.app.activity.mapper;

import java.util.List;

import com.yedam.app.activity.vo.ExpVO;

public interface ExpMapper {
	// 전체조회
	public List<ExpVO> selectExpList();
	// 단건조회
	public ExpVO selectExpInfo(ExpVO expVO);
	// 등록
	public int insertExp(ExpVO expVO);
	// 삭제
	public int deleteExp(String boardNo);
	// 게시글 수
	public int getCount();
}
