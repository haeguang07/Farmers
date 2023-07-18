package com.yedam.app.common.service;

import java.util.List;

import com.yedam.app.common.vo.ReviewVO;

public interface ReviewService {
	
	// 게시판별 리뷰조회
	public List<ReviewVO> getReview(String boardNo);

	// 리뷰등록
	public int addReview(ReviewVO vo);
}
