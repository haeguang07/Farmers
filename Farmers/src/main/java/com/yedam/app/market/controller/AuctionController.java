package com.yedam.app.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.market.service.AuctionService;
import com.yedam.app.market.vo.AuctionVO;

@Controller
public class AuctionController {
	
	@Autowired
	AuctionService actService;
	
	// 전체조회 페이지
	@GetMapping("auction")
	public String auctionList(Model model) {
		List<AuctionVO> list = actService.getAllAuctions();
		model.addAttribute("auction", list);
		// System.out.println(list);
		return "market/auction/auctionList";
	}
	
	// 단건조회 페이지
	@GetMapping("auctionInfo")
	public String auctionInfo(int actNo, Model model) {
		System.out.println(actNo);
		AuctionVO info = actService.getOneAuction(actNo);
		model.addAttribute("auctionInfo", info);
		return "market/auction/auctionInfo";
		
	}
	
	// 등록 페이지
	
	// 등록 처리
	
	// 수정 페이지
	
}
