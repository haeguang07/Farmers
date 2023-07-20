package com.yedam.app.common.mapper;

import java.util.List;

import com.yedam.app.common.vo.ReviewVO;

public interface ReviewMapper {

	//게시판별 리뷰조회
	public List<ReviewVO> getReview(String boardNo);

	//리뷰등록
	public int addReview(ReviewVO vo);
	
	// 게시글의 리뷰 수
	public int getCount(String boardNo);
}
