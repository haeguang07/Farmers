package com.yedam.app.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.market.service.CartService;
import com.yedam.app.market.vo.CartVO;

@Controller
public class CartCotroller {
	@Autowired
	CartService cartService;
	
	@GetMapping("cart")
	public String cartForm(CartVO vo, Model model){
		if(vo.getBoardCtg() == null) {
			System.out.println(vo.getBoardCtg());
			vo.setBoardCtg("n8");
			System.out.println(vo.getBoardCtg());
		}
		
		List<CartVO> list = cartService.getCartList(vo);
		model.addAttribute("cartList", list);
		return "market/cart/cartForm";
	}
}
