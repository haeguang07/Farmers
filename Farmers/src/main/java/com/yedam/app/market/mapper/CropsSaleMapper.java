package com.yedam.app.market.mapper;

import java.util.List;

import com.yedam.app.market.vo.CropsSaleVO;

public interface CropsSaleMapper {
	// 전체조회
	public List<CropsSaleVO> selectCropsSaleList(CropsSaleVO CsVO);
	// 단건조회
	public CropsSaleVO selectCropsSaleInfo(CropsSaleVO CsVO);
	// 등록
	public int insertCropsSale(CropsSaleVO CsVO);
	// 수정
	public int updateCropsSale(CropsSaleVO CsVO);
	// 삭제
	public int deleteCropsSale(String crpSaleNo);
}
