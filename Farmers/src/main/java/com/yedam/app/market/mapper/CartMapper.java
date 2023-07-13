package com.yedam.app.market.mapper;

import java.util.List;

import com.yedam.app.market.vo.CartVO;

public interface CartMapper {
	//장바구니 리스트 가져오기
	public List<CartVO> getCartList(CartVO vo);
}
