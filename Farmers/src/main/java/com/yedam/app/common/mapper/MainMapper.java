package com.yedam.app.common.mapper;

import java.util.List;

import com.yedam.app.common.vo.MainVO;

public interface MainMapper {
	
	//지역별 지도 이미지 조회
	public List<MainVO> selectImg(String dstCtg);
	
	

}
