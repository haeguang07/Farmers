package com.yedam.app.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.activity.service.ExpService;
import com.yedam.app.activity.vo.ExpVO;

@Controller
public class ExpController {
	
	@Autowired
	ExpService expService;
	
	// 전체조회
	@GetMapping("expList")
	public String getExpAllList(Model model) {
		List<ExpVO> list = expService.getExpList();
		model.addAttribute("expList", list);
		System.out.println(list);
		return "activity/exp/expList";
	}
	
	// 단건조회
	@GetMapping("expInfo")
	public String getExpInfo(Model model, ExpVO expVO) {
		System.out.println(expVO);
		ExpVO info = expService.getExpInfo(expVO);
		System.out.println(info);
		model.addAttribute("expInfo", info);
		return "activity/exp/expInfo";
	}
	
}
