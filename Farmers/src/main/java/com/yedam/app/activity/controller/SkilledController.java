package com.yedam.app.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.activity.service.SkilledService;

@Controller
public class SkilledController {

	@Autowired
	SkilledService skilledService;
	
	// 금손귀농인 메인 페이지 리턴
	@GetMapping("gold")
	public String skilledExpert(Model model){
		return "activity/skilled/skilledList";
	}
}
