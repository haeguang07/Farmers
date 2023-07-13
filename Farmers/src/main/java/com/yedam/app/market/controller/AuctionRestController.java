package com.yedam.app.market.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.market.service.AuctionService;
import com.yedam.app.market.vo.AuctionVO;
import com.yedam.app.market.vo.PageVO;

@RestController
public class AuctionRestController {
	
	@Autowired
	AuctionService actService;
	
	// 전체 경매 리스트(페이징)
	@GetMapping("auction")
	public Map<String, Object> auctionListPage(@RequestParam(required = false, defaultValue = "0") int page,
				@RequestParam(required = false, defaultValue = "인기순") String order) {
		
		Map<String, Object> map = new HashMap<>();
		
		page = (page == 0 ? 1 : page);
		List<AuctionVO> list = actService.getAuctionList(page, order);
		
		int total = actService.auctionTotal();
		PageVO dto = new PageVO(page, total);
		
		map.put("auctionList", list);
		map.put("page", dto);
		
		
		return map;
	}

 	// 다음 경매 리스트
	@GetMapping("auctionNext")
	public Map<String, Object> nextAuctionList(){
		
		Map<String, Object> map = new HashMap<>();
		
		List<AuctionVO> list = actService.getNextAuctionList();
		map.put("nextAuctionList", list);
		
		return map;
	}
	
}
