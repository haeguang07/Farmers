package com.yedam.app.activity.mapper;

import java.util.List;

import com.yedam.app.activity.vo.BnbVO;

public interface BnbMapper {
	
	//전체조회
	public List<BnbVO> selectBnbList();
	
}
