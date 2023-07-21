package com.yedam.app.market.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.app.market.vo.CropsSaleVO;

public interface CropsSaleMapper {
	// 전체조회(페이징)
	public List<CropsSaleVO> selectCropsSaleListPage(int page, String category, String order, String search);
	// 단건조회
	public CropsSaleVO selectCropsSaleInfo(CropsSaleVO csVO);
	// 등록
	public int insertCropsSale(CropsSaleVO csVO);
	// 수정
	public int updateCropsSale(CropsSaleVO csVO);
	// 삭제
	public int deleteCropsSale(String boardNo);
	// 게시글 수
	public int getCount(String category, String search);
	// 별점
	public Map<String, Object> totalRate(String boardNo);
}
