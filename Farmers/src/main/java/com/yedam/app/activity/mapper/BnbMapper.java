package com.yedam.app.activity.mapper;

import java.util.List;

import com.yedam.app.activity.vo.BnbVO;

public interface BnbMapper {
	
	//전체조회
	public List<BnbVO> selectBnbList(String dst1, int page, String order);
	
	//단건조회
	public BnbVO selectBnb(String bnbNo);
	
	//등록
	public int insertBnb(BnbVO vo);
	
	//수정
	public int updateBnb();
		
	//삭제
	public int deleteBnb();

	
	
}

