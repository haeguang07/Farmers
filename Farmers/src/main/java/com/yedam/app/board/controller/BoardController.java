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
	
	// 공지사항 페이지 이동
	@GetMapping("/noticeList")
	public String noticeList (Model model) {
		return "board/main/noticeList";
	}
	
	// 커뮤니티 페이지 이동
	@GetMapping("cmmnList")
	public String cmmnList(Model model) {
		model.addAttribute("dst1", codeService.getCodeList("0K"));
		return "board/main/cmmnList";
	}
	
	// 공지사항 상세 조회 페이지 이동
	@GetMapping("/noticeInfo")
	public String noticeInfo(Model model, String postCtg, String boardNo) {
		List<BoardVO> list = boardService.getBoardInfo(postCtg, boardNo);
		model.addAttribute("noticeInfo", list);
		
		return "board/main/noticeInfo";
	}
	
	// 커뮤니티 상세 조회 페이지 이동
	@GetMapping("cmmnInfo")
	public String cmmnInfo(Model model, String postCtg, String boardNo) {
		
		List<BoardVO> list = boardService.getBoardInfo(postCtg, boardNo);
		model.addAttribute("cmmnInfo", list);
		
		return "board/main/cmmnInfo";
	}

	// 이벤트 상세 조회 페이지 이동
	@GetMapping("eventInfo")
	public String eventInfo(Model model, String postCtg, String boardNo) {
		
		List<BoardVO> list = boardService.getBoardInfo(postCtg, boardNo);
		model.addAttribute("eventInfo", list);
		
		return "board/main/eventInfo";
	}
	
	// 커뮤니티 등록 페이지 이동
	@GetMapping("/add/Notice")
	public String addNotice() {
		return "board/main/addNotice";
	}
	
	// 커뮤니티 등록 페이지 이동
	@GetMapping("addCmmn")
	public String addCmmn(Model model) {
		model.addAttribute("dst1", codeService.getCodeList("0K"));
		return "board/main/addCmmn";
	}

	// 이벤트 등록 페이지 이동
	@GetMapping("addEvent")
	public String addEvent(Model model) {
		model.addAttribute("dst1", codeService.getCodeList("0K"));
		return "board/main/addEvent";
	}
	
	// 공지사항 수정 페이지 이동
	@GetMapping("/update/Notice")
	public String updateNotice(Model model, String postCtg, String boardNo) {
		model.addAttribute("noticeInfo", boardService.getBoardInfo(postCtg, boardNo));
		return "board/main/updateNotice";
	}
	
	// 커뮤니티 수정 페이지 이동
	@GetMapping("updateCmmn")
	public String updateCmmn(Model model, String postCtg, String boardNo, String dst1) {
		model.addAttribute("cmmnInfo", boardService.getBoardInfo(postCtg, boardNo));
		model.addAttribute("dst1", codeService.getCodeList("0K"));
		return "board/main/updateCmmn";
	}
	
	// 이벤트 수정 페이지 이동
	@GetMapping("updateEvent")
	public String updateEvent(Model model, String postCtg, String boardNo, String dst1) {
		model.addAttribute("eventInfo", boardService.getBoardInfo(postCtg, boardNo));
		model.addAttribute("dst1", codeService.getCodeList("0K"));
		model.addAttribute("dst2", codeService.getCodeList(dst1));
		return "board/main/updateEvent";
	}

}
