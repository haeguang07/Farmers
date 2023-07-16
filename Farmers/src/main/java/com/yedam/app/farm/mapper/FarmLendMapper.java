package com.yedam.app.farm.mapper;

import java.util.List;

import com.yedam.app.farm.vo.FarmLendVO;

public interface FarmLendMapper {
	// 전체조회
	public List<FarmLendVO> selectFarmLendList();
	// 전체조회(페이징)
	public List<FarmLendVO> selectFarmLendListPage();
	// 단건조회
	public FarmLendVO selectFarmLendInfo(FarmLendVO flVO);
	// 등록
	public int insertFarmLend(FarmLendVO flVO);
	// 수정
	public int updateFarmLend(FarmLendVO flVO);
	// 삭제
	public int deleteFarmLend(String frldNo);
	// 게시글 수
	public int getCount();
}
