package com.yedam.app.common.mapper;

import com.yedam.app.user.vo.AlertVO;

public interface AlertMapper {
	
	//알림 보내기
	public int addAlert(AlertVO vo);
}
