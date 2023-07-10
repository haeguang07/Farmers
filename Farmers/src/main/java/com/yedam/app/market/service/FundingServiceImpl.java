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
	
	//펀딩 리스트 조회
	@Override
	public List<FundingVO> getFundingList() {
		return fundingMapper.getFundingList();
	}
	
	//펀딩 리스트 조회 페이징
	@Override
	public List<FundingVO> getFundingListPage(int page,String category,String order,String search) {
		return fundingMapper.getFundingListPage(page,category,order,search);
	}
	
	//펀딩 총 갯수 조회
	@Override
	public int fundingTotal(String category,String search) {
		return fundingMapper.fundingTotal(category,search);
	}
	
	//펀딩 상세 조회
	@Override
	public FundingVO getFundingInfo(FundingVO vo) {
		return fundingMapper.getFundingInfo(vo);
	}

	@Override
	public List<FundingVO> getPolpularFnd() {
		return fundingMapper.getPolpularFnd();
	}


}
