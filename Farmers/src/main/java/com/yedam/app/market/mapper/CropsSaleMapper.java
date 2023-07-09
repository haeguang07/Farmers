package com.yedam.app.market.mapper;

import java.util.List;

import com.yedam.app.market.vo.CropsSaleVO;

public interface CropsSaleMapper {
	// 전체조회
	public List<CropsSaleVO> selectCropsSaleList();
	// 단건조회
	public CropsSaleVO selectCropsSaleInfo(CropsSaleVO csVO);
	// 등록
	public int insertCropsSale(CropsSaleVO csVO);
	// 수정
	public int updateCropsSale(CropsSaleVO csVO);
	// 삭제
	public int deleteCropsSale(String crpSaleNo);
}
