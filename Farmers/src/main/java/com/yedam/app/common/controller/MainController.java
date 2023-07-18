package com.yedam.app.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.common.service.MainService;
import com.yedam.app.common.vo.MainVO;

@Controller
@RequestMapping("main")
public class MainController {
	
	@Autowired
	MainService mainService;
	
	//작물지도
	@ResponseBody
	@GetMapping("/button")
	public List<MainVO> getMapImg(String dstCtg) {		
		return mainService.selectImg(dstCtg);
	}
	
	
	
	
	
	
	
}

