package com.yedam.app.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.activity.mapper.BnbMapper;
import com.yedam.app.activity.vo.BnbVO;

@Service
public class BnbServiceImpl implements BnbService {
	
	@Autowired
	BnbMapper bnbMapper; 
	
	@Override
	public List<BnbVO> selectBnbList() {
		return bnbMapper.selectBnbList();
	}
	
}

