package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.CartVO;

public interface CartService {
	// 장바구니 리스트 가져오기
	public List<CartVO> getCartList(CartVO vo);

	// 장바구니 삭제
	public boolean deleteCart(CartVO vo);
	
	//장바구니 등록
	public boolean insertCart(CartVO vo);

}
