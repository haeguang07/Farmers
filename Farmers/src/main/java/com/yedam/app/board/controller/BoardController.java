package com.yedam.app.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.vo.BoardVO;
import com.yedam.app.common.service.CodeService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;

	@Autowired
	CodeService codeService;

	// 이벤트 상세 조회 페이지 이동
	@GetMapping("eventInfo")
	public String eventInfo(Model model, String postCtg, String boardNo) {
		
		List<BoardVO> list = boardService.getBoardInfo(postCtg, boardNo);
		model.addAttribute("eventInfo", list);
		
		System.out.println(list);
		
		return "board/main/eventInfo";
	}
	
	// 이벤트 등록 페이지 이동
	@GetMapping("addEvent")
	public String addEvent() {
		return "board/main/addEvent";
	}

}
