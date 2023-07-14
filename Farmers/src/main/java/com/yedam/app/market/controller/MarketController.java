package com.yedam.app.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.market.service.MarketService;
import com.yedam.app.market.vo.MarketVO;

@Controller
public class MarketController {

	@Autowired
	MarketService mkService;

	// 전체조회 페이지
	@GetMapping("marketList")
	public String marketList(Model model) {
		return "market/market/marketList";
	}

	//
//	@GetMapping("marketItem")
//	@ResponseBody
//	public Map<String,Object> marketList() {
//		List<MarketVO> list = mkService.getMarketList();
//		Map<String, Object> map = new HashMap<>();
//		map.put("mk",list);
//		return map;
//	}
//	

	// 단건조회
	@GetMapping("mkInfo")
	public String getMkInfo(Model model, String mktNo) {
		model.addAttribute("mk", mkService.getMarketInfo(mktNo));
		return "market/market/marketInfo";
	}

	// 등록 페이지
	@GetMapping("addMk")
	public String addMkForm(Model model, MarketVO vo) {
		model.addAttribute("mk", new MarketVO());
		return "market/market/addMkForm";
	}

	// 등록처리
	@PostMapping("addMk")
	public String addMk(MarketVO vo) {
		System.out.println(vo);
		mkService.insertMk(vo);
		return "redirect:marketList";
	}

}
