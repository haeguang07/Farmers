package com.yedam.app.market.controller;

import java.util.List;

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
	@GetMapping("cropsSaleList")
	public String getCropsSaleAllList(Model model) {
		List<CropsSaleVO> list = csService.getCropsSaleList();
		model.addAttribute("csList", list);
		System.out.println(list);
		return "market/cropsSale/cropsSaleList";
	}
	
	// 단건조회
	@GetMapping("cropsSaleInfo")
	public String getCropsSaleInfo(Model model, CropsSaleVO csVO) {
		System.out.println(csVO);
		CropsSaleVO info = csService.getCropsSaleInfo(csVO);
		System.out.println(info);
		model.addAttribute("csInfo", info);
		return "market/cropsSale/cropsSaleInfo";
	}
}
