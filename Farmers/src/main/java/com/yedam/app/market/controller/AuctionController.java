package com.yedam.app.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuctionController {
	
	// 경매장 페이지 이동
	@GetMapping("auction")
	public String auction() {
		return "market/auction";
	}
	
	
}
