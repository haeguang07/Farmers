package com.yedam.app.market.service;

import java.util.List;

import com.yedam.app.market.vo.CropsSaleVO;

public interface CropsSaleService {
	// 전체조회
	public List<CropsSaleVO> getCropsSaleList(CropsSaleVO CsVO);
	// 단건조회
	public CropsSaleVO getCropsSaleInfo(CropsSaleVO CsVO);
	// 등록
	public int insertCropsSaleInfo(CropsSaleVO CsVO);
	// 수정
	public int updateCropsSaleInfo(CropsSaleVO CsVO);
	//삭제
	public int deleteCropsSaleInfo(String crpSaleNo);
}
