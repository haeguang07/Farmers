package com.yedam.app.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.common.mapper.MainMapper;
import com.yedam.app.common.vo.MainVO;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	MainMapper mainMapper;
	
	@Override
	public List<MainVO> selectImg(String dstCtg) {
		
		return mainMapper.selectImg(dstCtg);
	}

}
