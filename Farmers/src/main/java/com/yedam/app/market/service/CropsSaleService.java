package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.CropsSaleVO;

public interface CropsSaleService {
	// 전체조회
	public List<CropsSaleVO> getCropsSaleList();
	// 전체조회(페이징)
	public List<CropsSaleVO> getCropsSaleListPage(int page, String category, String order, String search);
	// 단건조회
	public CropsSaleVO getCropsSaleInfo(CropsSaleVO csVO);
	// 등록
	public boolean insertCropsSaleInfo(CropsSaleVO csVO);
	// 수정
	public int updateCropsSaleInfo(CropsSaleVO csVO);
	// 삭제
	public int deleteCropsSaleInfo(String crpSaleNo);
	// 게시글 수
	public int getCount(String category, String search);
	// 별점
	public int totalRate();
}
