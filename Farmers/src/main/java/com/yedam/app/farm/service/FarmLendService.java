package com.yedam.app.farm.service;

import java.util.List;

import com.yedam.app.farm.vo.FarmLendApplyVO;
import com.yedam.app.farm.vo.FarmLendVO;

public interface FarmLendService {
	// 전체조회(페이징)
	public List<FarmLendVO> getFarmLendListPage(int page, String dst1, String dst2, String mcrp, String price, String area);
	// 단건조회
	public FarmLendVO getFarmLendInfo(FarmLendVO flVO);
	// 등록
	public boolean insertFarmLendInfo(FarmLendVO flVO);
	// 수정
	public String updateFarmLendInfo(FarmLendVO flVO);
	// 삭제
	public int deleteFarmLendInfo(String boardNo);
	// 게시글 수
	public int getCount(String dst1, String dst2, String mcrp, String price, String area);
	// 농지 대여 신청(등록)
	public boolean insertFarmLendApply(FarmLendApplyVO flaVO);
}
