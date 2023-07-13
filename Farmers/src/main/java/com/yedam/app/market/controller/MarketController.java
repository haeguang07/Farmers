package com.yedam.app.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.market.service.MarketService;
import com.yedam.app.market.vo.MarketVO;

public class MarketController {

	@Autowired
	MarketService mkService;

	// 전체조회 페이지
	@GetMapping("market")
	public String marketList(Model model) {
		List<MarketVO> list = mkService.getMarketList();
		model.addAttribute("mkList", list);
		return "market/market/marketList";
	}

}
