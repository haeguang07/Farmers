package com.yedam.app.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> boardList(String postCtg, int pageNum, String dst1, String dst2) {
		return boardMapper.boardList(postCtg, pageNum, dst1, dst2);
	}
	
	@Override
	public int listCount(String postCtg) {
		return boardMapper.listCount(postCtg);
	}
	
	@Override
	public List<BoardVO> getBoardInfo(String postCtg, String boardNo) {
		return boardMapper.getBoardInfo(postCtg, boardNo);
	}
	
	@Override
	public boolean insertBoard(BoardVO vo) {
		return boardMapper.insertBoard(vo) > 0;
	}

	@Override
	public boolean updateBoard(BoardVO vo) {
		return boardMapper.updateBoard(vo) > 0;
	}
	
	@Override
	public boolean deleteBoard(String boardNo) {
		return boardMapper.deleteBoard(boardNo) > 0;
	}
	
}
