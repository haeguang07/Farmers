package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.CropsSaleVO;

public interface CropsSaleService {
	// 전체조회
	public List<CropsSaleVO> getCropsSaleList();
	// 단건조회
	public CropsSaleVO getCropsSaleInfo(CropsSaleVO csVO);
	// 등록
	public int insertCropsSaleInfo(CropsSaleVO csVO);
	// 수정
	public int updateCropsSaleInfo(CropsSaleVO csVO);
	// 삭제
	public int deleteCropsSaleInfo(String crpSaleNo);
	// 게시글 수
	public int getCount(String crpSaleNo);
	// 별점
	public int totalRate();
}
