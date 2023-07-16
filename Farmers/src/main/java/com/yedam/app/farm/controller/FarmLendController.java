package com.yedam.app.farm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.farm.service.FarmLendService;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.PageVO;

@Controller
public class FarmLendController {
	
	@Autowired
	FarmLendService flService;
	
	// 리스트 전체조회
	@GetMapping("farmLendList")
	public String getFarmLendAllList(Model model) {
		return "farm/farmLend/farmLendList";
	}
	
	// 리스트 전체조회(페이징)
	@PostMapping("farmLendList")
	@ResponseBody
	public Map<String, Object> getFarmLendListPage(@RequestParam(required = false, defaultValue = "0") int pageNum, 
												   @RequestParam(required = false) String addr,
												   @RequestParam(required = false) String daddr,
												   @RequestParam(required = false) String mcrp,
												   @RequestParam(required = false) String price,
												   @RequestParam(required = false) String area) {
		pageNum = pageNum == 0 ? 1 : pageNum;
		int total = flService.getCount(addr, daddr, mcrp, price, area);
		List<FarmLendVO> list = flService.getFarmLendListPage(pageNum, addr, daddr, mcrp, price, area);
		
		PageVO vo = new PageVO(pageNum, total);
		Map<String, Object> map = new HashMap<>();
		map.put("flList", list);
		map.put("pageInfo", vo);
		
		return map;
	}
	
	// 단건조회
	@GetMapping("farmLendInfo")
	public String getFarmLendInfo(FarmLendVO flVO, Model model) {
		FarmLendVO info = flService.getFarmLendInfo(flVO);
		model.addAttribute("flInfo", info);
		return "";
	}
	
	// 등록 페이지 불러오기
	@GetMapping("farmLendInsert")
	public String insertFarmLendForm() {
		return "farm/farmLend/farmLendInsert";
	}
	
	// 등록 기능
	@PostMapping("farmLendInsert")
	@ResponseBody
	public String insertFarmLend(FarmLendVO flVO) {
		boolean result = flService.insertFarmLendInfo(flVO);
		
		if(result) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 수정 페이지 불러오기
	@GetMapping("farmLendUpdate")
	public String updateFarmLendForm(FarmLendVO flVO, Model model) {
		FarmLendVO find = flService.getFarmLendInfo(flVO);
		model.addAttribute("flInfo", find);
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
	public String deleteFarmLend(String frldNo) {
		flService.deleteFarmLendInfo(frldNo);
		return "farm/farmLend/farmLendList";
	}
}
