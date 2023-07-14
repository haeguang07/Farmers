package com.yedam.app.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.common.service.PaymentService;
import com.yedam.app.common.vo.MemberDetailVO;
import com.yedam.app.common.vo.payProductVO;

@Controller
public class PaymenetController {
	@Autowired
	PaymentService paymentService;
	
	@GetMapping("payment")
	public String payment( @RequestParam(value = "productList[]") List<payProductVO> productList) {
		for(payProductVO vo: productList) {
		    System.out.println(vo);
		}
		return "common/payment/payment";
	}
	
	@PostMapping("getMemberData")
	@ResponseBody
	public MemberDetailVO getMemberData(String memNo){
		return paymentService.getMemberData(memNo);
	}
}
