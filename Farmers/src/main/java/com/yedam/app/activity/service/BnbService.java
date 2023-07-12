package com.yedam.app.activity.service;

import java.util.List;

import com.yedam.app.activity.vo.BnbVO;

public interface BnbService {
	
	public List<BnbVO> selectBnbList();
	
	public BnbVO selectBnb(String bnbNo);

}
