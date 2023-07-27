package com.yedam.app.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.activity.mapper.SkilledMapper;
import com.yedam.app.activity.vo.SkilledVO;

@Service
public class SkilledServiceImpl implements SkilledService {

	@Autowired
	SkilledMapper skilledMapper;
	
	@Override
	public List<SkilledVO> skilledList(String div, int pageNum, String dst1, String dst2) {
		return skilledMapper.skilledList(div, pageNum, dst1, dst2);
	}

	@Override
	public int listCount(String div) {
		return skilledMapper.listCount(div);
	}

	@Override
	public SkilledVO skilledInfo(String boardNo) {
		return skilledMapper.skilledInfo(boardNo);
	}

	@Override
	public boolean addSkilled(SkilledVO vo) {
		return skilledMapper.addSkilled(vo) > 0;
	}

	@Override
	public boolean updateSkilled(SkilledVO vo) {
		return skilledMapper.updateSkilled(vo) > 0;
	}

	@Override
	public boolean deleteSkilled(String boardNo) {
		return skilledMapper.deleteSkilled(boardNo) > 0;
	}

	@Override
	public boolean applySkilled(SkilledVO vo) {
		return skilledMapper.applySkilled(vo) > 0;
	}

}
