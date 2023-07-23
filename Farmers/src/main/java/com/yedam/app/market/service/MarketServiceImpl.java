package com.yedam.app.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.market.mapper.MarketMapper;
import com.yedam.app.market.vo.MarketVO;

@Service
public class MarketServiceImpl implements MarketService {

	@Autowired
	MarketMapper mkMapper;

	@Override
	public List<MarketVO> getMarketList(String mktCtg, int page, String order) {
		return mkMapper.getMarketList(mktCtg,page,order);
	}

	@Override
	public MarketVO getMarketInfo(String boardNo) {
		return mkMapper.getMarketInfo(boardNo);
	}

	@Override
	public int selectCount(String mktCtg) {
		return mkMapper.selectCount(mktCtg);
	}

	@Override
	public int insertMk(MarketVO vo) {
		return mkMapper.insertMk(vo);
	}

	@Override
	public int updateMk(MarketVO vo) {
		return mkMapper.updateMk(vo);
	}

	@Override
	public int deleteMk(String boardNo) {
		return mkMapper.deleteMk(boardNo);
	}


}
