package com.yedam.app.activity.service;

import java.util.List;

import com.yedam.app.activity.vo.SkilledVO;

public interface SkilledService {

	// 구해요/할게요 전체 조회
	public List<SkilledVO> skilledList(String div, int pageNum, String dst1, String dst2, String title, String memNo);
	// 게시글 총 갯수 조회
	public int listCount(String div, String dst1, String dst2, String title, String memNo);
	// 상세 조회
	public SkilledVO skilledInfo(String boardNo);
	// 게시글 등록
	public boolean addSkilled(SkilledVO vo);
	// 게시글 수정
	public boolean updateSkilled(SkilledVO vo);
	// 게시글 삭제
	public boolean deleteSkilled(String boardNo);
	// 신청
	public boolean applySkilled(SkilledVO vo);
	// 신청인 조회
	public List<SkilledVO> applyList(String boardNo);
	
}
