package com.yedam.app.activity.controller;

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

import com.yedam.app.activity.service.BnbService;
import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.market.vo.PageVO;

@Controller
public class BnbController {

	@Autowired
	BnbService bnbService;

	// 전체조회
	@GetMapping("bnbList")
	public String getBnbList(Model model) {
		return "activity/bnb/bnbList";
	}

	// 전체조회- 조건별 정렬
	@GetMapping("bnbItem")
	@ResponseBody
	public Map<String, Object> getBnbList(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false) String region,
			@RequestParam(required = false, defaultValue = "최신순") String order) {
		
		List<BnbVO> list = bnbService.selectBnbList(region, page, order);
		int total = bnbService.selectCount(region);
		PageVO vo = new PageVO(page, total);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bnb", list);
		map.put("page", vo);
		return map;
	}

	// 단건조회
	@GetMapping("bnbInfo")
	public String getBnbInfo(Model model, String bnbNo) {
		model.addAttribute("bnb", bnbService.selectBnb(bnbNo));
		return "activity/bnb/bnbInfo";
	}

	// 등록 페이지
	@GetMapping("insertBnb")
	public String addBnbForm(Model model, BnbVO vo) {
		model.addAttribute("bnb", new BnbVO());
		return "activity/bnb/addBnbForm";
	}

	// 등록처리
	@PostMapping("insertBnb")
	public String addBnb(BnbVO vo) {
		System.out.println(vo);
		bnbService.insertBnb(vo);
		return "redirect:bnbList";
	}

}
