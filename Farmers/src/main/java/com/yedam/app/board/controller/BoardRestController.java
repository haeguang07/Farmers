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
import com.yedam.app.common.service.CodeService;
import com.yedam.app.market.vo.PageVO;

@RestController
public class BoardRestController {

	@Autowired
	BoardService boardService;
	@Autowired
	CodeService codeService;
	
	// 게시판 폼
	@GetMapping("boardForm")
	public ModelAndView boardForm() {
		//model.addAttribute("dst1", codeService.getCodeList("0K"));
		ModelAndView mv = new ModelAndView("board/main/eventList");
		mv.addObject("dst1", codeService.getCodeList("0K"));
		return mv;
	}
	
	// 메인 게시판 전체 조회
	@GetMapping("boardList")
	public Map<String, Object> boardList(@RequestParam(required = false, defaultValue = "i2")String postCtg
										 , @RequestParam(required = false, defaultValue = "0") int pageNum
										 , @RequestParam(required = false) String dst1
										 , @RequestParam(required = false) String dst2){
		
		Map<String, Object> map = new HashMap<>();
		
		pageNum = (pageNum == 0 ? 1 : pageNum);
		int total = boardService.listCount(postCtg);
		
		PageVO vo = new PageVO(pageNum, total);
		List<BoardVO> list = boardService.boardList(postCtg, pageNum, dst1, dst2);

		map.put("boardList", list);
		map.put("pageInfo", vo);
		if(dst1 != null) {
			map.put("dst2", codeService.getCodeList(dst1));
		}
		
		return map;
	}
	
	// 게시판 상세 조회 페이지
	@GetMapping("boardInfoForm")
	public ModelAndView boardInfoForm() {
		ModelAndView mv = new ModelAndView("board/main/eventInfo");
		return mv;
	}
	
	// 게시판 단건 데이터 전달
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
