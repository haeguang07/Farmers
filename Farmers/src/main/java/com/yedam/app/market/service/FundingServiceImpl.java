package com.yedam.app.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.market.mapper.FundingMapper;
import com.yedam.app.market.vo.FundingVO;

@Service
public class FundingServiceImpl implements FundingService {
	@Autowired
	FundingMapper fundingMapper;
	
	@Override
	public List<FundingVO> getFundingList() {
		return fundingMapper.getFundingList();
	}

	@Override
	public FundingVO getFundingInfo(FundingVO vo) {
		return fundingMapper.getFundingInfo(vo);
	}

}
