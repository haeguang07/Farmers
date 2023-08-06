package com.yedam.app.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	
	@Override
	public void buyAuctionAlert(AuctionVO vo) {
		auctionMapper.buyAuctionAlert(vo);
	}

	@Override
	public void bidAuctionAlert(AuctionVO vo) {
		auctionMapper.bidAuctionAlert(vo);
	}
	
	@Scheduled(cron = "0 0 0 * * ?") // 매일 자정마다 실행 스케줄러
	@Override
	public void expiredAuction() {
		auctionMapper.expiredAuction();
		System.out.println("111 expiredAuction exe");
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	@Override
	public void expiredAuctionPay() {
		auctionMapper.expiredAuctionPay();	
		System.out.println("222 expiredAuctionPay exe");
	}
}
