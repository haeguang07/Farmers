package com.yedam.app.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.market.mapper.CropsSaleMapper;
import com.yedam.app.market.vo.CropsSaleVO;

@Service
public class CropsSaleServiceImpl implements CropsSaleService {
	
	@Autowired
	CropsSaleMapper csMapper;
	
	@Override
	public List<CropsSaleVO> getCropsSaleList(CropsSaleVO CsVO) {
		return csMapper.selectCropsSaleList(CsVO);
	}

	@Override
	public CropsSaleVO getCropsSaleInfo(CropsSaleVO CsVO) {
		return csMapper.selectCropsSaleInfo(CsVO);
	}

	@Override
	public int insertCropsSaleInfo(CropsSaleVO CsVO) {
		int result = csMapper.insertCropsSale(CsVO);
		
		if(result == 1) {
			return Integer.parseInt(CsVO.getCrpSaleNo());
		} else {
			return -1;
		}
	}

	@Override
	public int updateCropsSaleInfo(CropsSaleVO CsVO) {
		int result = csMapper.updateCropsSale(CsVO);
		
		if(result == 1) {
			return Integer.parseInt(CsVO.getCrpSaleNo());
		} else {
			return -1;
		}
	}

	@Override
	public int deleteCropsSaleInfo(String crpSaleNo) {
		int result = csMapper.deleteCropsSale(crpSaleNo);
		
		if(result == 1) {
			return Integer.parseInt(crpSaleNo);
		} else {
			return -1;
		}
	}

}
