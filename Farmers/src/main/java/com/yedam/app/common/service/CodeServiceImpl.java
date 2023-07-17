package com.yedam.app.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.common.mapper.CodeMapper;
import com.yedam.app.common.vo.CodeVO;

@Service
public class CodeServiceImpl implements CodeService {
	
	@Autowired
	CodeMapper codeMapper;
	
	// 하나의 그룹 코드에 대한 상세 코드 조회
	@Override
	public List<CodeVO> getCodeList(String ccNo) {
		return codeMapper.getCodeList(ccNo);
	}
	
	// 여러 그룹 코드들의 상세 코드 조회
	@Override
	public Map<String, List<CodeVO>> getCodes(String ... ccNolist) {
		Map<String, List<CodeVO>> map = new HashMap<>();
		for(String ccNo : ccNolist) {
			map.put(ccNo, codeMapper.getCodeList(ccNo));
		}
		return map;
	}


}
