package com.yedam.app.common.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import com.yedam.app.common.vo.PaymentDetailVO;
import com.yedam.app.common.vo.PaymentVO;

@Controller
public class PaymenetController {
	@Autowired
	PaymentService paymentService;

	@GetMapping("payment") //requestBody 사용 가능
	public String payment(@RequestParam(value = "productList") String productList, Model model) {

		// json으로 받은 결제상품정보 List<PayProductVO>로 변환
		Gson gson = new Gson();
		List<PaymentDetailVO> list = gson.fromJson(productList, new TypeToken<ArrayList<PaymentDetailVO>>() {
		}.getType());
		for (PaymentDetailVO vo : list) {
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

	// 결제처리 페이지
	@GetMapping("payLoad")
	public String payLoadPage() {
		return "common/payment/payLoad";
	}

	// 결제 정보 입력
	@PostMapping("insertPayment")
	@ResponseBody
	public Map<String, String> insertPayment(@RequestParam(value = "paymentData") String data) {
		Gson gson = new Gson();
		PaymentVO vo = gson.fromJson(data, PaymentVO.class);
		System.out.println(vo);
		paymentService.insertPaymnet(vo);
		System.out.println(vo);

		return Collections.singletonMap("payNo", vo.getPayNo());
	}

	// 결제 상세 정보 입력
	@PostMapping("insertPayDetail")
	@ResponseBody
	public String insertPayDetail(@RequestParam(value = "payDetailData") String data) {
		System.out.println(data);
		Gson gson = new Gson();
		List<PayProductVO> list = gson.fromJson(data, new TypeToken<ArrayList<PayProductVO>>() {
		}.getType());
		for (PayProductVO vo : list) {
			System.out.println(vo);
			paymentService.insertPayDetail(vo);
		}

		return "success";
	}

	// 결제 완료 시 이동 페이지
	@GetMapping("paySuccess")
	public String successPage(String result, Model model) {
		model.addAttribute("result", result);
		return "common/payment/paySuccess";
	}


}
