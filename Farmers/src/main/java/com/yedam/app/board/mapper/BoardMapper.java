package com.yedam.app.board.mapper;

import java.util.List;

import com.yedam.app.board.vo.BoardVO;

public interface BoardMapper {

	// 메인 게시판 전체 조회
	public List<BoardVO> boardList(String postCtg);
	// 메인 게시판 상세 조회
	public List<BoardVO> getBoardInfo(BoardVO vo);
	
}
