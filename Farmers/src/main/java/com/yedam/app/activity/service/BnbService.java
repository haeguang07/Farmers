package com.yedam.app.activity.service;

import java.util.List;

import com.yedam.app.activity.vo.BnbVO;

public interface BnbService {
	
	public List<BnbVO> selectBnbList(String dst1, int page, String order);
	
	public BnbVO selectBnb(String bnbNo);
	
	public int insertBnb(BnbVO vo);

}
