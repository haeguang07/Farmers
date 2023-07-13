package com.yedam.app.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.market.service.CartService;
import com.yedam.app.market.vo.CartVO;

@Controller
public class CartCotroller {
	@Autowired
	CartService cartService;
	
	//장바구니 폼
	@GetMapping("cart")
	public String cartForm(CartVO vo, Model model){
		return "market/cart/cartForm";
	}
	
	//장바구니 리스트 조회
	@PostMapping("cart")
	@ResponseBody
	public List<CartVO> getCartList(CartVO vo){
		if(vo.getBoardCtg() == null) {
			System.out.println(vo.getBoardCtg());
			vo.setBoardCtg("n8");
			System.out.println(vo.getBoardCtg());
		}
		List<CartVO> list = cartService.getCartList(vo);
		return list;
	}
}
