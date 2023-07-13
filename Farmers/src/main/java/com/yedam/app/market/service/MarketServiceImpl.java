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
	public List<MarketVO> getMarketList() {
		return mkMapper.getMarketList();
	}

}
