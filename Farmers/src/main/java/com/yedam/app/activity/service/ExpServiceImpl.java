package com.yedam.app.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.activity.mapper.ExpMapper;
import com.yedam.app.activity.vo.ExpVO;

@Service
public class ExpServiceImpl implements ExpService {
	
	@Autowired
	ExpMapper expMapper;

	@Override
	public List<ExpVO> getExpList() {
		return expMapper.selectExpList();
	}

	@Override
	public ExpVO getExpInfo(ExpVO expVO) {
		return expMapper.selectExpInfo(expVO);
	}

	@Override
	public int insertExpInfo(ExpVO expVO) {
		int result = expMapper.insertExp(expVO);
		
		if(result == 1) {
			return Integer.parseInt(expVO.getBoardNo());
		} else {
			return -1;
		}
	}

	@Override
	public int deleteExpInfo(String boardNo) {
		int result = expMapper.deleteExp(boardNo);
		
		if(result == 1) {
			return Integer.parseInt(boardNo);
		} else {
			return -1;
		}
	}

	@Override
	public int getCount() {
		return expMapper.getCount();
	}
	
}
