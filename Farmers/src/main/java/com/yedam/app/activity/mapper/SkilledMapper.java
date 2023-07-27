package com.yedam.app.activity.mapper;

import java.util.List;

import com.yedam.app.activity.vo.SkilledVO;

public interface SkilledMapper{

	// 구해요/할게요 전체 조회
	public List<SkilledVO> skilledList(String div, int pageNum, String dst1, String dst2);
	// 게시글 총 갯수 조회
	public int listCount(String div);
	// 상세 조회
	public SkilledVO skilledInfo(String boardNo);
	// 게시글 등록
	public int addSkilled(SkilledVO vo);
	// 게시글 수정
	public int updateSkilled(SkilledVO vo);
	// 게시글 삭제
	public int deleteSkilled(String boardNo);
	// 신청
	public int applySkilled(SkilledVO vo);
	
}
