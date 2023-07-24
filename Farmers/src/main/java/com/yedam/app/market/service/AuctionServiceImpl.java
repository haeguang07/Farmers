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
	public List<AuctionVO> getAuctionList(int page, String order) {
		return auctionMapper.getAuctionListPage(page, order);
	}
	
	@Override
	public int auctionTotal() {
		return auctionMapper.auctionTotal();
	}

	@Override
	public List<AuctionVO> getNextAuctionList() {
		return auctionMapper.getNextAuctionList();
	}
	
	@Override
	public AuctionVO getAuctionInfo(String boardNo) {
		return auctionMapper.selectAuction(boardNo);
	}
	
	@Override
	public boolean bidAuction(AuctionVO vo) {
		return auctionMapper.bidAuction(vo) > 0;
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
	public boolean deleteAuction(String boardNo) {
		
		return false;
	}

	@Override
	public boolean updateAuctionReg() {
		
		return auctionMapper.updateAuctionReg()>0;
	}

}
