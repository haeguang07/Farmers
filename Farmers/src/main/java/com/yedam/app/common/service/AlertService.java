package com.yedam.app.common.service;

import java.util.List;

import com.yedam.app.user.vo.AlertVO;

public interface AlertService {
	// 알림 보내기
	public boolean addAlert(AlertVO vo);

	// 나의 알림 조회
	public List<AlertVO> myAlert(String memNo);
}
