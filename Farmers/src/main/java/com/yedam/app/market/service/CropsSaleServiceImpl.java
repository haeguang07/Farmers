package com.yedam.app.market.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.market.mapper.CropsSaleMapper;
import com.yedam.app.market.vo.CropsSaleVO;

@Service
public class CropsSaleServiceImpl implements CropsSaleService {
	
	@Autowired
	CropsSaleMapper csMapper;

	@Override
	public List<CropsSaleVO> getCropsSaleListPage(int page, String category, String order, String search) {
		return csMapper.selectCropsSaleListPage(page, category, order, search);
	}
	
	@Override
	public CropsSaleVO getCropsSaleInfo(CropsSaleVO csVO) {
		return csMapper.selectCropsSaleInfo(csVO);
	}

	@Override
	public boolean insertCropsSaleInfo(CropsSaleVO csVO) {
		return csMapper.insertCropsSale(csVO) > 0;
	}

	@Override
	public String updateCropsSaleInfo(CropsSaleVO csVO) {
		int result = csMapper.updateCropsSale(csVO);
		
		if(result == 1) {
			return csVO.getBoardNo();
		} else {
			return null;
		}
	}

	@Override
	public int deleteCropsSaleInfo(String boardNo) {
		return csMapper.deleteCropsSale(boardNo);
	}
	
	@Override
	public int getCount(String category, String search) {
		return csMapper.getCount(category, search);
	}

	@Override
	public Map<String, Object> totalRate(String boardNo) {
		return csMapper.totalRate(boardNo);
	}


}
