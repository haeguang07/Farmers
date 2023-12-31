package com.yedam.app.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.common.mapper.AlertMapper;
import com.yedam.app.user.vo.AlertVO;

@Service
public class AlertServiceImpl implements AlertService {
	@Autowired
	AlertMapper alertMapper; 
	
	@Override
	public boolean addAlert(AlertVO vo) {
		return alertMapper.addAlert(vo) > 0;
	}

	@Override
	public List<AlertVO> myAlert(String memNo) {
		return alertMapper.myAlert(memNo);
	}

}
