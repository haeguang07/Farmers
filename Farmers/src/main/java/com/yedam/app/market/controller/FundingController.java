package com.yedam.app.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.market.service.FundingService;
import com.yedam.app.market.vo.FundingVO;
import com.yedam.app.market.vo.PageVO;

@Controller
public class FundingController {
	@Autowired
	FundingService fundingService;
	
	
	@GetMapping("fundingList")
	public String fundingList(Model model) {
		List<FundingVO> list = fundingService.getFundingList();
		model.addAttribute("fundingList", list);
		System.out.println(list);
		return "market/funding/fundingList";
	}
	
//	@GetMapping("fundingList")
//	public String fundingListPage(PageVO page , Model model) {
//		int pageNum = page.getPageNum();
//		
//		pageNum = (pageNum == 0 ? 1 : pageNum);
//		
//		int total = fundingService.fundingTotal();
//		List<FundingVO> list = fundingService.getFundingListPage(pageNum);
//		
//		PageVO vo = new PageVO(pageNum, total);
//		model.addAttribute("fundinglist", list);
//		model.addAttribute("pageInfo", vo);
//		
//		return "market/funding/fundingList";
//	}
	
	
	@GetMapping("fundingInfo")
	public String fundingInfo(FundingVO vo, Model model) {
		System.out.println(vo);
		FundingVO info =  fundingService.getFundingInfo(vo);
		System.out.println(info);
		model.addAttribute("fundingInfo",info);
		return "market/funding/fundingInfo";
	}		
	
	@GetMapping("addFunding")
	public String addFunding() {
		return "market/funding/addFundingForm";
	}
}
