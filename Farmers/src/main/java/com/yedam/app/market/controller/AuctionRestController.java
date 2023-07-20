package com.yedam.app.market.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Map<String, Object> auctionListPage(@RequestParam(required = false, defaultValue = "0") int pageNum,
				@RequestParam(required = false, defaultValue = "인기순") String order) {
		
		Map<String, Object> map = new HashMap<>();
		
		pageNum = (pageNum == 0 ? 1 : pageNum);
		List<AuctionVO> list = actService.getAuctionList(pageNum, order);
		
		int total = actService.auctionTotal();
		PageVO dto = new PageVO(pageNum, total);
		
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
	
	// 입찰하기
	@PostMapping("bidAuction")
	public Map<String, Object> bidAuction(@RequestBody AuctionVO vo){
		
		Map<String, Object> map = new HashMap<>();
		boolean result = actService.bidAuction(vo);
		
		if(result) {
			map.put("retCode", "Success");
		}else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	
	// 경매 등록
	@PostMapping("insertAuction")
	public Map<String, Object> InsertAuction(AuctionVO vo){
		
		System.out.println(vo);
		 
		Map<String, Object> map = new HashMap<>();
		boolean result = actService.InsertAuction(vo);
				
		if(result) {
			map.put("retCode", "Success");
		}else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	
}
