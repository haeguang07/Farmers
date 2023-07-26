package com.yedam.app.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	// 이벤트 전체 조회
	@GetMapping("eventList")
	public ModelAndView eventList() {
		// model.addAttribute("dst1", codeService.getCodeList("0K"));
		ModelAndView mv = new ModelAndView("board/main/eventList");
		mv.addObject("dst1", codeService.getCodeList("0K"));
		return mv;
	}
	

	// 메인 게시판 전체 조회
	@GetMapping("boardList")
	public Map<String, Object> boardList(@RequestParam(required = false, defaultValue = "i2") String postCtg,
			@RequestParam(required = false, defaultValue = "0") int pageNum,
			@RequestParam(required = false) String dst1, @RequestParam(required = false) String dst2) {

		Map<String, Object> map = new HashMap<>();

		pageNum = (pageNum == 0 ? 1 : pageNum);
		int total = boardService.listCount(postCtg);

		System.out.println(dst2);
		
		PageVO vo = new PageVO(pageNum, total);
		List<BoardVO> list = boardService.boardList(postCtg, pageNum, dst1, dst2);

		map.put("boardList", list);
		map.put("pageInfo", vo);
		if (dst1 != null) {
			map.put("dst2", codeService.getCodeList(dst1));
		}

		return map;
	}

	
//	// 이벤트 상세 조회 값
//	@GetMapping("eventInfoDetail")
//	public Map<String, Object> eventInfoDetail(String postCtg, String boardNo){
//		
//		Map<String, Object> map = new HashMap<>();
//		List<BoardVO> list = boardService.getBoardInfo(postCtg, boardNo);
//		
//		map.put("eventInfo", list);
//		
//		System.out.println("111111111111" + map);
//		System.out.println("222222222222" + list);
//		
//		return map;
//	}
	
	// 지역2 코드 반환
	@GetMapping("getCode")
	public Map<String, Object> getCode(@RequestParam(required = false) String dst1){
		Map<String, Object> map = new HashMap<>();
		
		map.put("dst2", codeService.getCodeList(dst1));
		
		return map;
	}
	
	// 공지사항 등록
	@PostMapping("addNotice")
	public Map<String, Object> addNotice(BoardVO vo) {
		Map<String, Object> map = new HashMap<>();

		if (boardService.insertBoard(vo)) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}

		return map;
	}
	
	// 이벤트 등록
	@PostMapping("addEvent")
	public Map<String, Object> addEvent(BoardVO vo){
		Map<String, Object> map = new HashMap<>();
		
		System.out.println(vo);
		
		if(boardService.insertBoard(vo)) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	
	// 이벤트 수정
	@PostMapping("updateEvent")
	public Map<String, Object> updateEvent(BoardVO vo){
		
		Map<String, Object> map = new HashMap<>();
		
		if(boardService.updateBoard(vo)) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	
	// 이벤트 삭제
	@DeleteMapping("deleteEvent")
	public Map<String, Object> deleteEvent(String boardNo){
		Map<String, Object> map = new HashMap<>();
		
		if(boardService.deleteBoard(boardNo)) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}

}
