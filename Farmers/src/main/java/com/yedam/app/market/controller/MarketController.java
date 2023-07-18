package com.yedam.app.market.controller;

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

import com.yedam.app.market.service.MarketService;
import com.yedam.app.market.vo.MarketVO;
import com.yedam.app.market.vo.PageVO;

@Controller
public class MarketController {

	@Autowired
	MarketService mkService;

	// 전체조회 페이지
	@GetMapping("marketList")
	public String marketList(Model model) {
		return "market/market/marketList";
	}

	//전체조회-조건별 정렬
	@GetMapping("marketItem")
	@ResponseBody
	public Map<String,Object> marketList(
		@RequestParam(required = false) String mktCtg,
		@RequestParam(required = false, defaultValue = "1") int page,
		@RequestParam(required = false, defaultValue = "최신순") String order) {

		List<MarketVO> list = mkService.getMarketList(mktCtg,page,order);
		int total = mkService.selectCount(mktCtg);
		PageVO vo = new PageVO(page,total);
		
		Map<String, Object> map = new HashMap<>();
		map.put("mk",list);
		map.put("page",vo);
		return map;
	}
	

	// 단건조회
	@GetMapping("mkInfo")
	public String getMkInfo(Model model, String boardNo) {
		model.addAttribute("mk", mkService.getMarketInfo(boardNo));
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
