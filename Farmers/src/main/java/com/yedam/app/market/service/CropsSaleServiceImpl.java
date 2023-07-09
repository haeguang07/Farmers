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
	public List<CropsSaleVO> getCropsSaleList() {
		return csMapper.selectCropsSaleList();
	}

	@Override
	public CropsSaleVO getCropsSaleInfo(CropsSaleVO csVO) {
		return csMapper.selectCropsSaleInfo(csVO);
	}

	@Override
	public int insertCropsSaleInfo(CropsSaleVO csVO) {
		int result = csMapper.insertCropsSale(csVO);
		
		if(result == 1) {
			return Integer.parseInt(csVO.getCrpSaleNo());
		} else {
			return -1;
		}
	}

	@Override
	public int updateCropsSaleInfo(CropsSaleVO csVO) {
		int result = csMapper.updateCropsSale(csVO);
		
		if(result == 1) {
			return Integer.parseInt(csVO.getCrpSaleNo());
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
