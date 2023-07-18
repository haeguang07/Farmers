package com.yedam.app.common.mapper;

import java.util.List;

import com.yedam.app.common.vo.ReviewVO;

public interface ReviewMapper {
	
	public List<ReviewVO> selectReview(String boardNo); 

}
