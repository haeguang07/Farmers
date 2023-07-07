package com.yedam.app.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestCon {
	@GetMapping("test")
	public String test() {
		return "index";
	} 
	
	
}
