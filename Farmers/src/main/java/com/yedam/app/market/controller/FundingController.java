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

import com.yedam.app.market.service.FundingService;
import com.yedam.app.market.vo.FundingVO;
import com.yedam.app.market.vo.PageVO;

@Controller
public class FundingController {
	@Autowired
	FundingService fundingService;
	
	//기본 리스트 출력
//	@GetMapping("fundingList")
//	public String fundingList(Model model) {
//		List<FundingVO> list = fundingService.getFundingList();
//		model.addAttribute("fundingList", list);
//		System.out.println(list);
//		return "market/funding/fundingList";
//	}
	
	//펀딩 리스트 폼
	@GetMapping("fundingList")
	public String fundingList(Model model) {
		return "market/funding/fundingList";
	}
	
	//페이징 pageVO 방식 
	@PostMapping("fundingList")
	@ResponseBody
	public Map<String,Object> fundingListPage( @RequestParam(required = false, defaultValue = "0") int pageNum, 
			 								   @RequestParam(required = false) String category,
			 								   @RequestParam(required = false, defaultValue = "최신순") String order,
			 								   @RequestParam(required = false) String search ,
			 								   Model model) {	
		System.out.println(category);
		System.out.println(order);
		System.out.println(search);
		
		pageNum = (pageNum == 0 ? 1 : pageNum);
		int total = fundingService.fundingTotal(category,search);
		System.out.println(total);
		List<FundingVO> list = fundingService.getFundingListPage(pageNum,category,order,search);
		
		PageVO vo = new PageVO(pageNum, total);
		Map<String,Object> map = new HashMap<String, Object>();
 		map.put("fundingList", list);
 		map.put("pageInfo", vo);
		
		return map;
	}
	
	//펀딩 상세 페이지
	@GetMapping("fundingInfo")
	public String fundingInfo(FundingVO vo, Model model) {
		FundingVO info =  fundingService.getFundingInfo(vo);
		System.out.println(info);
		List<FundingVO> poplu = fundingService.getPolpularFnd();
		model.addAttribute("fundingInfo",info);
		model.addAttribute("polurInfo",poplu);
		return "market/funding/fundingInfo";
	}		
	
	//펀딩 등록 페이지
	@GetMapping("addFunding")
	public String addFunding() {
		return "market/funding/addFundingForm";
	}
	
	
}
