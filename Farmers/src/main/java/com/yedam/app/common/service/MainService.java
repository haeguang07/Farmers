package com.yedam.app.common.service;

import java.util.List;

import com.yedam.app.common.vo.MainVO;

public interface MainService {
	
	//작물지도 출력
	public List<MainVO> selectImg(String dstCtg);
	

}

