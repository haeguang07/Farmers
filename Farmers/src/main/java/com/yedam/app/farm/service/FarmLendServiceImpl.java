package com.yedam.app.farm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.farm.mapper.FarmLendMapper;
import com.yedam.app.farm.vo.FarmLendVO;

@Service
public class FarmLendServiceImpl implements FarmLendService {
	
	@Autowired
	FarmLendMapper flMapper;
	
	@Override
	public List<FarmLendVO> getFarmLendList() {
		return flMapper.selectFarmLendList();
	}

	@Override
	public List<FarmLendVO> getFarmLendListPage(int page, String dst1, String dst2, String mcrp, String price, String area) {
		return flMapper.selectFarmLendListPage(page, dst1, dst2, mcrp, price, area);
	}

	@Override
	public FarmLendVO getFarmLendInfo(FarmLendVO flVO) {
		return flMapper.selectFarmLendInfo(flVO);
	}

	@Override
	public boolean insertFarmLendInfo(FarmLendVO flVO, String first, String second, String third) {
		return flMapper.insertFarmLend(flVO, first, second, third) > 0;
	}

	@Override
	public String updateFarmLendInfo(FarmLendVO flVO) {
		int result = flMapper.updateFarmLend(flVO);
		
		if(result == 1) {
			return flVO.getBoardNo();
		} else {
			return null;			
		}
	}

	@Override
	public int deleteFarmLendInfo(String boardNo) {
		return flMapper.deleteFarmLend(boardNo);
	}

	@Override
	public int getCount(String dst1, String dst2, String mcrp, String price, String area) {
		return flMapper.getCount(dst1, dst2, mcrp, price, area);
	}

}
