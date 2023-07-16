package com.yedam.app.farm.service;

import java.util.List;

import com.yedam.app.farm.vo.FarmLendVO;

public interface FarmLendService {
	// 전체조회
	public List<FarmLendVO> getFarmLendList();
	// 전체조회(페이징)
	public List<FarmLendVO> getFarmLendListPage();
	// 단건조회
	public FarmLendVO getFarmLendInfo(FarmLendVO flVO);
	// 등록
	public boolean insertFarmLendInfo(FarmLendVO flVO);
	// 수정
	public String updateFarmLendInfo(FarmLendVO flVO);
	// 삭제
	public int deleteFarmLendInfo(String frldNo);
	// 게시글 수
	public int getCount();
}
