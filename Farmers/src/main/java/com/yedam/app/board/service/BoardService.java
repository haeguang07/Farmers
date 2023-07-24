package com.yedam.app.board.service;

import java.util.List;

import com.yedam.app.board.vo.BoardVO;

public interface BoardService {

	// 메인 게시판 전체 조회
	public List<BoardVO> boardList(String postCtg);
	// 메인 게시판 상세 조회
	public List<BoardVO> getBoardInfo(BoardVO vo);
	// 등록
	public boolean addNotice(BoardVO vo); 
	
}
