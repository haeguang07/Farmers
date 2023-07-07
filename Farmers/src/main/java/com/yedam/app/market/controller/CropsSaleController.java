package com.yedam.app.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.market.service.CropsSaleService;
import com.yedam.app.market.vo.CropsSaleVO;

@Controller
public class CropsSaleController {
	
	@Autowired
	CropsSaleService csService;
	
	// 전체조회
	@GetMapping("CropsSaleList")
	public String getCropsSaleAllList(Model model, CropsSaleVO CsVO) {
		model.addAttribute("csList", csService.getCropsSaleList(CsVO));
		return "market/cropsSale/cropsSaleList";
	}
}
