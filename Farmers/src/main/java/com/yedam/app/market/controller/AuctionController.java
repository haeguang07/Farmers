package com.yedam.app.market.controller;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.market.service.AuctionService;
import com.yedam.app.market.vo.AuctionVO;

@Controller
public class AuctionController {
	
	@Autowired
	AuctionService actService;
	@Autowired
	StringEncryptor jasyptStringEncryptor;
	
	//매일 자정 상태 변경
	@Scheduled(cron = "0 0 0 * * ?") 
	public void update() {
		boolean result=actService.updateAuctionReg();
		System.out.println(result);
	}
	
	// 전체조회 페이지
	@GetMapping("auctionList")
	public String auctionList() {
//		List<AuctionVO> list = actService.getAuctionList();
//		model.addAttribute("actList", list);
//		 System.out.println(list);
		return "market/auction/auctionList";
	}
	
	// 단건조회 페이지
	@GetMapping("auctionInfo")
	public String auctionInfo(Model model, String boardNo) {
		AuctionVO info = actService.getAuctionInfo(boardNo);
		AuctionVO vo = actService.sellerInfo(boardNo);
		vo.setMemZip(jasyptStringEncryptor.decrypt(vo.getMemZip()));
		vo.setMemAdr(jasyptStringEncryptor.decrypt(vo.getMemAdr()));
		vo.setMemAdrdeta(jasyptStringEncryptor.decrypt(vo.getMemAdrdeta()));
		vo.setEmail(jasyptStringEncryptor.decrypt(vo.getEmail()));
		vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
		model.addAttribute("seller", vo);
		model.addAttribute("actInfo", info);

		return "market/auction/auctionInfo";
		
	}
	
	// 등록 페이지
	@GetMapping("/add/Auction")
	public String addAuction() {
		return "market/auction/addAuction";
	}
	
	
}
