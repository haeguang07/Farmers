package com.yedam.app.board.service;

import java.util.List;

import com.yedam.app.board.vo.BoardVO;

public interface BoardService {

	public List<BoardVO> boardList(BoardVO vo);
	
}
