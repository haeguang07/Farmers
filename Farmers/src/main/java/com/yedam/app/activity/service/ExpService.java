package com.yedam.app.activity.service;

import java.util.List;

import com.yedam.app.activity.vo.ExpVO;

public interface ExpService {
	// 전체조회
	public List<ExpVO> getExpList();
	// 단건조회
	public ExpVO getExpInfo(ExpVO expVO);
	// 등록
	public int insertExpInfo(ExpVO expVO);
	// 삭제
	public int deleteExpInfo(String frexNo);
}
