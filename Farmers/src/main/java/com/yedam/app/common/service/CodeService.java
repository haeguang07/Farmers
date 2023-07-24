package com.yedam.app.common.service;

import java.util.List;
import java.util.Map;

import com.yedam.app.common.vo.CodeVO;

public interface CodeService {
	// 하나의 그룹 코드에 대한 상세 코드 조회
	public List<CodeVO> getCodeList(String ccNo);
	// 여러 그룹 코드들의 상세 코드 조회
	public Map<String, List<CodeVO>> getCodes(String ... ccNolist);

}

