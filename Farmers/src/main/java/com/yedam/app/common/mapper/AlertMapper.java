package com.yedam.app.common.mapper;

import java.util.List;

import com.yedam.app.user.vo.AlertVO;

public interface AlertMapper {
	
	//알림 보내기
	public int addAlert(AlertVO vo);
	
	//나의 알림 조회
	public List<AlertVO> myAlert (String memNo);
}
