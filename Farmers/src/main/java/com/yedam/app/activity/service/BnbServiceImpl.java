package com.yedam.app.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.yedam.app.activity.mapper.BnbMapper;
import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.activity.vo.DateVO;

@Service
public class BnbServiceImpl implements BnbService {

	@Autowired
	BnbMapper bnbMapper;

	@Override
	public List<BnbVO> selectBnbList(String dst1, int page, String order) {
		return bnbMapper.selectBnbList(dst1, page, order);
	}

	@Override
	public BnbVO selectBnb(String boardNo) {
		return bnbMapper.selectBnb(boardNo);
	}

	@Override
	public int selectCount(String dst1) {
		return bnbMapper.selectCount(dst1);
	}

	@Override
	public int insertBnb(BnbVO vo) {
		return bnbMapper.insertBnb(vo);
	}

	@Override
	public int updateBnb(BnbVO vo) {
		return bnbMapper.updateBnb(vo);
	}

	@Override
	public int deleteBnb(String boardNo) {
		return bnbMapper.deleteBnb(boardNo);
	}

	@Override
	public List<String> rsvDateCheck(String boardNo) {
		return bnbMapper.rsvDateCheck(boardNo);
	}

	@Scheduled(cron = "0 0 6 * * *") //초 분 시 일 월 요일
	@Override
	public int updateEndDate() {
		System.out.println("스케쥴 실행");
		return bnbMapper.updateEndDate();
	}

}
