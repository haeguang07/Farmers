package com.yedam.app.market.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.market.service.MarketService;
import com.yedam.app.market.vo.MarketVO;

public class MarketController {

	@Autowired
	MarketService mkService;

	// 전체조회 페이지
	@GetMapping("marketList")
	public String marketList(Model model) {
		return "market/market/marketList";
	}
	
	//
	@GetMapping("marketItem")
	@ResponseBody
	public Map<String,Object> marketList() {
		List<MarketVO> list = mkService.getMarketList();
		Map<String, Object> map = new HashMap<>();
		map.put("mk",list);
		return map;
	}
	

}
