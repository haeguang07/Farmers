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
		model.addAttribute(list);
		return "activity/bnb/bnbList";		
	}
	
	//단건조회
	@GetMapping("bnbInfo")
	public String getBnbInfo(Model model){
		List<BnbVO> list = bnbService.selectBnbList();
		model.addAttribute(list);
		return "activity/bnb/bnbInfo";		
	}
	

}
