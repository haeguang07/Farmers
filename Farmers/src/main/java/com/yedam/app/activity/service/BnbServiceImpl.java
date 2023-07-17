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
	public List<BnbVO> selectBnbList(String dst1, int page, String order) {
		return bnbMapper.selectBnbList(dst1, page, order);
	}

	@Override
	public BnbVO selectBnb(String boardNo) {
		return bnbMapper.selectBnb(boardNo);
	}

	@Override
	public int selectCount(String dst1) {
		return bnbMapper.selectCount(dst1);
	}

	@Override
	public int insertBnb(BnbVO vo) {
		return bnbMapper.insertBnb(vo);
	}
}
