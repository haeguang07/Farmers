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
	public List<BoardVO> boardList(String postCtg) {
		return boardMapper.boardList(postCtg);
	}
	
	@Override
	public List<BoardVO> getBoardInfo(BoardVO vo) {
		return boardMapper.getBoardInfo(vo);
	}
	
	@Override
	public boolean addNotice(BoardVO vo) {
		return boardMapper.addNotice(vo) > 0;
	}

	
}
