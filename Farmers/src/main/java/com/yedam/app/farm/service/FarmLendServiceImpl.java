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
	public List<FarmLendVO> getFarmLendListPage() {
		return flMapper.selectFarmLendListPage();
	}

	@Override
	public FarmLendVO getFarmLendInfo(FarmLendVO flVO) {
		return flMapper.selectFarmLendInfo(flVO);
	}

	@Override
	public boolean insertFarmLendInfo(FarmLendVO flVO) {
		return flMapper.insertFarmLend(flVO) > 0;
	}

	@Override
	public String updateFarmLendInfo(FarmLendVO flVO) {
		int result = flMapper.updateFarmLend(flVO);
		
		if(result == 1) {
			return flVO.getFrldLendNo();
		} else {
			return null;			
		}
	}

	@Override
	public int deleteFarmLendInfo(String frldNo) {
		return flMapper.deleteFarmLend(frldNo);
	}

	@Override
	public int getCount() {
		return flMapper.getCount();
	}

}
