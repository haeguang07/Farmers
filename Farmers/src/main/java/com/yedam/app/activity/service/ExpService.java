package com.yedam.app.activity.service;

import java.util.List;

import com.yedam.app.activity.vo.DateVO;
import com.yedam.app.activity.vo.ExpApplyVO;
import com.yedam.app.activity.vo.ExpVO;

public interface ExpService {
	// 전체조회
	public List<ExpVO> getExpListPage(int page, String expStart, String dst1, String dst2);

	// 단건조회
	public ExpVO getExpInfo(ExpVO expVO);

	// 등록
	public boolean insertExpInfo(ExpVO expVO);

	// 삭제
	public int deleteExpInfo(String boardNo);

	// 게시글 수
	public int getCount(String expStart, String dst1, String dst2);

	// 농촌 체험하기 신청(등록)
	public boolean insertExpApply(ExpApplyVO expaVO);

}
