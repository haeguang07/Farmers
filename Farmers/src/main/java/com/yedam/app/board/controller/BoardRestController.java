package com.yedam.app.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	// 메인 게시판 전체 조회
	@GetMapping("boardList")
	public Map<String, Object> boardList(@RequestParam(required = false, defaultValue = "i2") String postCtg){
		
		Map<String, Object> map = new HashMap<>();
		
		List<BoardVO> list = boardService.boardList(postCtg);
		
		map.put("boardList", list);
		
		return map;
	}
	
	// 메인 게시판 상세 조회
	@GetMapping("boardInfo")
	public Map<String, Object> boardInfo(BoardVO vo){
		
		Map<String, Object> map = new HashMap<>();
		
		List<BoardVO> list = boardService.getBoardInfo(vo);
		
		map.put("boardInfo", list);
		
		return map;
	}
	
	// 게시판 등록
	@PostMapping("addNotice")
	public Map<String, Object> addNotice(BoardVO vo){
		Map<String, Object> map = new HashMap<>();
		
		if(boardService.addNotice(vo)) {
			map.put("retCode", "Success");
		}else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	
}
