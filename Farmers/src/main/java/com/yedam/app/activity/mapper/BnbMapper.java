package com.yedam.app.activity.mapper;

import java.util.List;

import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.activity.vo.DateVO;

public interface BnbMapper {
	
	//전체조회
	public List<BnbVO> selectBnbList(String dst1, int page, String order);
	
	//단건조회
	public BnbVO selectBnb(String boardNo);
	
	//총 갯수
	public int selectCount(String dst1);
	
	//등록
	public int insertBnb(BnbVO vo);
	
	//수정
	public int updateBnb(BnbVO vo);
		
	//삭제
	public int deleteBnb(String boardNo);
	
	//예약 날짜 확인
	public List<String> rsvDateCheck(String boardNo);
	
}

