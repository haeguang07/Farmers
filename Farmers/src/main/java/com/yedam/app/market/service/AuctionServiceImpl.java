package com.yedam.app.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.market.mapper.AuctionMapper;
import com.yedam.app.market.vo.AuctionVO;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	AuctionMapper auctionMapper;
	
	@Override
	public List<AuctionVO> getAuctionList() {
		return auctionMapper.showAuctionList();
	}

	@Override
	public AuctionVO getOneAuction(int actNo) {
		return auctionMapper.selectAuction(actNo);
	}

	@Override
	public AuctionVO calHighestBid(int actNo) {
		return auctionMapper.calHighestBid(actNo);
	}
	
	@Override
	public boolean InsertAuction(AuctionVO vo) {
		return auctionMapper.addAuction(vo) > 0;
	}

	@Override
	public boolean updateAuction(AuctionVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAuction(int ActNo) {
		// TODO Auto-generated method stub
		return false;
	}

}
