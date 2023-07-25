package com.yedam.app.common.service;

import com.yedam.app.user.vo.AlertVO;

public interface AlertService {
	// 알림 보내기
	public boolean addAlert(AlertVO vo);
}
