package com.yedam.app.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.activity.service.BnbService;
import com.yedam.app.activity.vo.BnbVO;

@Controller
public class BnbController {
	
	@Autowired
	BnbService bnbService; 
	
	//전체조회
	@GetMapping("bnbList")
	public String getBnbList(Model model){
		List<BnbVO> list = bnbService.selectBnbList();
		model.addAttribute("list",list);
		return "activity/bnb/bnbList";		
	}
	
	//단건조회
	@GetMapping("bnbInfo")
	public String getBnbInfo(Model model,String bnbNo){
		model.addAttribute("bnb",bnbService.selectBnb(bnbNo));
		return "activity/bnb/bnbInfo";		
	}
	
	//등록 페이지
	@GetMapping("addBnbForm")
	public String addBnbForm(Model model, BnbVO vo) {
		return "activity/bnb/addBnbForm";		
	}
	

}
