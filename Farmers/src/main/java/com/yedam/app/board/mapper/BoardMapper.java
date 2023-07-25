package com.yedam.app.board.mapper;

import java.util.List;

import com.yedam.app.board.vo.BoardVO;

public interface BoardMapper {

	// 메인 게시판 전체 조회
	public List<BoardVO> boardList(String postCtg, int pageNum, String dst1, String dst2);
	// 총 게시물 카운트
	public int listCount(String postCtg);
	// 메인 게시판 상세 조회
	public List<BoardVO> getBoardInfo(BoardVO vo);
	// 등록
	public int addNotice(BoardVO vo); 
}
