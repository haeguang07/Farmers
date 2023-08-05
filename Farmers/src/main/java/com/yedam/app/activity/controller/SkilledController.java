package com.yedam.app.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.activity.service.SkilledService;
import com.yedam.app.activity.vo.SkilledVO;
import com.yedam.app.common.service.CodeService;
import com.yedam.app.market.vo.PageVO;
import com.yedam.app.user.vo.MemberVO;

@Controller
public class SkilledController {

	@Autowired
	SkilledService skilledService;
	
	@Autowired
	CodeService codeService;
	
	// 금손귀농인 메인 페이지 리턴
	@GetMapping("/skilled")
	public String skilledExpert(Model model){
		model.addAttribute("dst1", codeService.getCodeList("0K"));
		return "activity/skilled/skilledList";
	}
	
	// 시 구분 출력 리스트
	@PostMapping("/dst2")
	@ResponseBody
	public Map<String, Object> dst2List(@RequestParam(required = false) String dst1){
		Map<String, Object> map = new HashMap<>();
		map.put("dst2", codeService.getCodeList(dst1));
		return map;
	}
	
	// 전체 리스트 조회
	@GetMapping("/skilledList")
	@ResponseBody
	public Map<String, Object> skilledList(@RequestParam(required = false) String div,
			@RequestParam(required = false, defaultValue = "0") int pageNum,
			@RequestParam(required = false) String dst1, 
			@RequestParam(required = false) String dst2,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String memNo) {

		pageNum = (pageNum == 0 ? 1 : pageNum);
		List<SkilledVO> list = skilledService.skilledList(div, pageNum, dst1, dst2, title, memNo);
		int total = skilledService.listCount(div, dst1, dst2, title, memNo);
		PageVO vo = new PageVO(pageNum, total);

		Map<String, Object> map = new HashMap<>();
		map.put("gold", list);
		map.put("page", vo);
		if (dst1 != null) {
			map.put("dst2", codeService.getCodeList(dst1));
		}
		
		return map;
	}
	
	// 등록 모달창 이동
	@GetMapping("/add/Skilled")
	public String skilledInsert() {
		return "activity/skilled/insertSkilled";
	}
	
	// 등록 처리
	@PostMapping("/add/Skilled")
	@ResponseBody
	public Map<String, Object> insertSkilled(SkilledVO vo){
		Map<String, Object> map = new HashMap<>();
		boolean result = skilledService.addSkilled(vo);
		if(result) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}
		return map;
	}
	
	
	// 상세 조회 모달창 이동
	@GetMapping("/skilledInfo")
	public String skilledInfo(String boardNo, Model model, HttpServletRequest req) {
		model.addAttribute("goldInfo", skilledService.skilledInfo(boardNo));
		HttpSession session = req.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("mem");
		String currentMemNo = vo.getMemNo();
		List<SkilledVO> aplList = skilledService.applyList(boardNo);
		String result = "N";
		for(SkilledVO apl : aplList) {
			if(apl.getMemNo().equals(currentMemNo)) {
				result = "Y";
			}
		}
		model.addAttribute("applyer", result);
		return "activity/skilled/skilledInfo";
	}
	
	// 수정 모달창 이동
	@GetMapping("/update/Skilled")
	public String updateSkilled(String boardNo, Model model) {
		model.addAttribute("dst1", codeService.getCodeList("0K"));
		String dst1 = skilledService.skilledInfo(boardNo).getDst1();
		model.addAttribute("dst2", codeService.getCodeList(dst1));
		model.addAttribute("goldInfo", skilledService.skilledInfo(boardNo));
		return "activity/skilled/updateSkilled";
	}
	
	// 수정 처리
	@PostMapping("/update/Skilled")
	@ResponseBody
	public Map<String, Object> updateSkilled(SkilledVO vo){
		Map<String, Object> map = new HashMap<>();
		boolean result = skilledService.updateSkilled(vo);
		
		if(result) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	
	// 삭제 처리
	@PostMapping("/deleteSkilled")
	@ResponseBody
	public Map<String, Object> deleteSkilled(@RequestParam(required = false) String boardNo){
		Map<String, Object> map = new HashMap<>();
		boolean result = skilledService.deleteSkilled(boardNo);
			
		if(result) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	
	// 신청 모달창 이동
	@GetMapping("/apply/Skilled")
	public String applySkilled(String boardNo, Model model) {
		model.addAttribute("goldInfo", skilledService.skilledInfo(boardNo));
		return "activity/skilled/applySkilled";
	}
	
	// 신청 처리
	@PostMapping("/apply/Skilled")
	@ResponseBody
	public Map<String, Object> applyComplete(SkilledVO vo){
		Map<String, Object> map = new HashMap<>();
		boolean result = skilledService.applySkilled(vo);
		
		if(result) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	
	
}
