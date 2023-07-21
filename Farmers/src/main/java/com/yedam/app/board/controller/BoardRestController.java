package com.yedam.app.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.vo.BoardVO;

@RestController
public class BoardRestController {

	@Autowired
	BoardService boardService;
	
	// 게시판 폼
	@GetMapping("boardForm")
	public ModelAndView boardForm() {
		ModelAndView mv = new ModelAndView("board/main/boardForm");
		return mv;
	}
	
	// 메인 게시판 전체 조회 및 단건 조회
	@GetMapping("boards")
	public Map<String, Object> boardList(BoardVO vo){
		
		Map<String, Object> map = new HashMap<>();
		
		List<BoardVO> list = boardService.boardList(vo);
		
		map.put("boardList", list);
		
		return map;
	}
	
}
