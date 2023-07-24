package com.yedam.app.farm.mapper;

import java.util.List;

import com.yedam.app.farm.vo.FarmLendVO;

public interface FarmLendMapper {
	// 전체조회(페이징)
	public List<FarmLendVO> selectFarmLendListPage(int page, String dst1, String dst2, String mcrp, String price, String area);
	// 단건조회
	public FarmLendVO selectFarmLendInfo(FarmLendVO flVO);
	// 등록
	public int insertFarmLend(FarmLendVO flVO);
	// 수정
	public int updateFarmLend(FarmLendVO flVO);
	// 삭제
	public int deleteFarmLend(String boardNo);
	// 게시글 수
	public int getCount(String dst1, String dst2, String mcrp, String price, String area);
	// 농지 대여 신청(등록)
	public int insertFarmLendApply(String memNo);
}
