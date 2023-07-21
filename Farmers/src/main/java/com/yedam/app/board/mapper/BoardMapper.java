package com.yedam.app.board.mapper;

import java.util.List;

import com.yedam.app.board.vo.BoardVO;

public interface BoardMapper {

	public List<BoardVO> boardList(BoardVO vo);
	
}
