package com.yedam.app.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.activity.service.ExpService;
import com.yedam.app.activity.vo.ExpApplyVO;
import com.yedam.app.activity.vo.ExpVO;
import com.yedam.app.common.service.CodeService;
import com.yedam.app.farm.vo.PageDTO;

@Controller
public class ExpController {
	
	@Autowired
	ExpService expService;
	
	@Autowired
	CodeService codeService;
	
	// 리스트 전체조회
	@GetMapping("expList")
	public String getExpAllList(Model model) {
		model.addAttribute("dst1", codeService.getCodeList("0K"));
		return "activity/exp/expList";
	}
	
	// 전체조회
	@PostMapping("expList")
	@ResponseBody
	public Map<String, Object> getExpListPage(ExpVO expVO) {
		int pageNum = expVO.getPage() == 0 ? 1 : expVO.getPage();
		int total = expService.getCount(expVO);
		List<ExpVO> list = expService.getExpListPage(pageNum, expVO);
		
		PageDTO page = new PageDTO(pageNum, total);
		Map<String, Object> map = new HashMap<>();
		map.put("expList", list);
		map.put("pageInfo", page);
		if(expVO.getDst1() != null) {
			map.put("dst2", codeService.getCodeList(expVO.getDst1()));
		}
		
		return map;
	}
	
	// 단건조회
	@GetMapping("expInfo")
	public String getExpInfo(ExpVO expVO, Model model) {
		ExpVO info = expService.getExpInfo(expVO);
		model.addAttribute("expInfo", info);
		model.addAttribute("codeInfo", codeService.getCodeList("0N"));
		model.addAttribute("apply", codeService.getCodeList("0E"));
		return "activity/exp/expInfo";
	}
	
	// 등록 페이지 불러오기
	@GetMapping("add/expInsert")
	public String insertExpForm() {
		return "activity/exp/expInsert";
	}
	
	// 등록 기능
	@PostMapping("add/expInsert")
	@ResponseBody
	public String insertExp(ExpVO expVO) {
		boolean result = expService.insertExpInfo(expVO);
		
		if(result) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 삭제 기능
	@PostMapping("expDelete")
	@ResponseBody
	public String deleteExp(String boardNo) {
		expService.deleteExpInfo(boardNo);
		return "success";
	}
	
	// 신청(등록) 기능
	@PostMapping("expApply")
	@ResponseBody
	public String ExpApply(ExpApplyVO expaVO) {
		boolean result = expService.insertExpApply(expaVO);
		
		if(result) {
			return "success";			
		} else {
			return "fail";
		}
	}
}
