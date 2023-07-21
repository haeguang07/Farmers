package com.yedam.app.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.common.mapper.ReviewMapper;
import com.yedam.app.common.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewMapper revMapper;

	@Override
	public List<ReviewVO> getReview(String boardNo) {
		return revMapper.getReview(boardNo);
	}

	@Override
	public int addReview(ReviewVO vo) {
		return revMapper.addReview(vo);
	}

	@Override
	public int getCount(String boardNo) {
		return revMapper.getCount(boardNo);
	}

	@Override
	public int delReview(String revNo) {
		return revMapper.delReview(revNo);
	}

}
