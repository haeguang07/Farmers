package com.yedam.app.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.activity.mapper.ExpMapper;
import com.yedam.app.activity.vo.DateVO;
import com.yedam.app.activity.vo.ExpApplyVO;
import com.yedam.app.activity.vo.ExpVO;

@Service
public class ExpServiceImpl implements ExpService {
	
	@Autowired
	ExpMapper expMapper;

	@Override
	public List<ExpVO> getExpListPage(int page, String expStart, String dst1, String dst2) {
		return expMapper.selectExpListPage(page, expStart, dst1, dst2);
	}

	@Override
	public ExpVO getExpInfo(ExpVO expVO) {
		return expMapper.selectExpInfo(expVO);
	}

	@Override
	public boolean insertExpInfo(ExpVO expVO) {
		return expMapper.insertExp(expVO) > 0;
	}

	@Override
	public int deleteExpInfo(String boardNo) {
		int result = expMapper.deleteExp(boardNo);
		
		if(result == 1) {
			return result;
		} else {
			return -1;
		}
	}

	@Override
	public int getCount(String expStart, String dst1, String dst2) {
		return expMapper.getCount(expStart, dst1, dst2);
	}

	@Override
	public boolean insertExpApply(ExpApplyVO expaVO) {
		return expMapper.insertExpApply(expaVO) > 0;
	}

	
}
