package com.yedam.app.common.mapper;

import java.util.List;

import com.yedam.app.common.vo.CodeVO;

public interface CodeMapper {
	// 상세 코드 조회
	public List<CodeVO> getCodeList(String ccNo);
}
