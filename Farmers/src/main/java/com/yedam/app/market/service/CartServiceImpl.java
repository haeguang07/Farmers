package com.yedam.app.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.market.mapper.CartMapper;
import com.yedam.app.market.vo.CartVO;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartMapper cartMapper;
	
	@Override
	public List<CartVO> getCartList(CartVO vo) {
		return cartMapper.getCartList(vo);
	}

}
