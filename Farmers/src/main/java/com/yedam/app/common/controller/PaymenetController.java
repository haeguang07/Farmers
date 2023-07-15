package com.yedam.app.common.controller;

import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yedam.app.common.service.PaymentService;
import com.yedam.app.common.vo.MemberPayVO;
import com.yedam.app.common.vo.PayProductVO;

@Controller
public class PaymenetController {
	@Autowired
	PaymentService paymentService;

	@GetMapping("payment")
	public String payment(@RequestParam(value = "productList") String productList, Model model) {

		// json으로 받은 결제상품정보 List<PayProductVO>로 변환
		Gson gson = new Gson();
		List<PayProductVO> list = gson.fromJson(productList, new TypeToken<ArrayList<PayProductVO>>() {
		}.getType());
		for (PayProductVO vo : list) {
			System.out.println(vo);
			paymentService.getProductInfo(vo);
			System.out.println(vo);
		}
		System.out.println(list);
		model.addAttribute("productList", list);
		return "common/payment/payment";
	}

	// 결제 회원 정보 가져오기
	@PostMapping("getMemberData")
	@ResponseBody
	public MemberPayVO getMemberData(String memNo) {
		return paymentService.getMemberData(memNo);
	}

	//결제성공 페이지
	@GetMapping("successPage")
	public String successPage() {
		return "common/payment/paySuccess";
	}
	
	
	// map으로 procedure 값 받아오기 테스트
	@GetMapping("procedureTest1")
	@ResponseBody
	public Map<String, Object> test1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardNo", "CRP000001");
		map.put("boardCtg", "n8");
		map.put("title", "");
		map.put("price", "");
		map.put("rep", "");
		// paymentService.getProductInfo(map);
		System.out.println(map);
		return map;
	}

	// vo로 프로시저 값 받아오기 테스트
	@GetMapping("procedureTest2")
	@ResponseBody
	public PayProductVO test2() {
		PayProductVO vo = new PayProductVO();
		vo.setBoardNo("CRP000001");
		vo.setBoardCtg("n8");
		paymentService.getProductInfo(vo);
		System.out.println(vo);
		return vo;
	}


}
