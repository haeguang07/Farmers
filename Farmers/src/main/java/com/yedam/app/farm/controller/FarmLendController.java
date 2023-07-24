package com.yedam.app.farm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.common.service.CodeService;
import com.yedam.app.farm.service.FarmLendService;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.PageVO;
import com.yedam.app.user.service.AttachService;

@Controller
public class FarmLendController {
	
	@Autowired
	FarmLendService flService;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	AttachService attachService;
	
	// 리스트 전체조회
	@GetMapping("farmLendList")
	public String getFarmLendAllList(Model model) {
		model.addAttribute("dst1", codeService.getCodeList("0K"));
		model.addAttribute("mcrp", codeService.getCodeList("1E"));
		model.addAttribute("price", codeService.getCodeList("1C"));
		model.addAttribute("area", codeService.getCodeList("1D"));
		return "farm/farmLend/farmLendList";
	}
	
	// 리스트 전체조회(페이징)
	@PostMapping("farmLendList")
	@ResponseBody
	public Map<String, Object> getFarmLendListPage(@RequestParam(required = false, defaultValue = "0") int pageNum, 
												   @RequestParam(required = false) String dst1,
												   @RequestParam(required = false) String dst2,
												   @RequestParam(required = false) String mcrp,
												   @RequestParam(required = false) String price,
												   @RequestParam(required = false) String area) {
		pageNum = pageNum == 0 ? 1 : pageNum;
		int total = flService.getCount(dst1, dst2, mcrp, price, area);
		List<FarmLendVO> list = flService.getFarmLendListPage(pageNum, dst1, dst2, mcrp, price, area);
		
		PageVO vo = new PageVO(pageNum, total);
		Map<String, Object> map = new HashMap<>();
		map.put("flList", list);
		map.put("pageInfo", vo);
		if(dst1 != null) {
			map.put("dst2", codeService.getCodeList(dst1));
		}
		
		return map;
	}
	
	// 단건조회
	@GetMapping("farmLendInfo")
	public String getFarmLendInfo(FarmLendVO flVO, Model model) {
		FarmLendVO info = flService.getFarmLendInfo(flVO);
//		System.out.println(info);
		model.addAttribute("flInfo", info);
		return "farm/farmLend/farmLendInfo";
	}
	
	// 등록 페이지 불러오기
	@GetMapping("farmLendInsert")
	public String insertFarmLendForm(Model model) {
		model.addAttribute("mcrp", codeService.getCodeList("1E"));
		return "farm/farmLend/farmLendInsert";
	}
	
	// 등록 기능
	@PostMapping("farmLendInsert")
	@ResponseBody
	public String insertFarmLend(@RequestBody FarmLendVO flVO) {
		flService.insertFarmLendInfo(flVO);
		System.out.println(flVO);
		
		return "success";
	}
	
	// 수정 페이지 불러오기
	@GetMapping("farmLendUpdate")
	public String updateFarmLendForm(FarmLendVO flVO, Model model) {
		FarmLendVO find = flService.getFarmLendInfo(flVO);
//		System.out.println(find);
		model.addAttribute("flInfo", find);
//		model.addAttribute("attach", attachService.getAttachList(boardNo));
		return "farm/farmLend/farmLendUpdate";
	}
	
	// 수정 기능
	@PostMapping("farmLendUpdate")
	@ResponseBody
	public Map<String, Object> updateFarmLend(FarmLendVO flVO) {
		boolean result = false;
		String frldNo = flService.updateFarmLendInfo(flVO);
		if(frldNo != null) {
			result = true;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("frldNo", frldNo);
		
		return map;
	}
	
	// 삭제 기능
	@PostMapping("farmLendDelete")
	@ResponseBody
	public String deleteFarmLend(String boardNo) {
		flService.deleteFarmLendInfo(boardNo);
		return "farm/farmLend/farmLendList";
	}
	
	// 신청 페이지 불러오기
	@GetMapping("farmLendApply")
	public String FarmLendApplyForm(FarmLendVO flVO, Model model) {
		model.addAttribute("flInfo", flService.getFarmLendInfo(flVO));
		return "farm/farmLend/farmLendApply";
	}
	
	// 신청 기능
	@PostMapping("farmLendApply")
	@ResponseBody
	public String FarmLendApply(String memNo, Model model) {
		flService.insertFarmLendApply(memNo);
		return "success";
	}
}
