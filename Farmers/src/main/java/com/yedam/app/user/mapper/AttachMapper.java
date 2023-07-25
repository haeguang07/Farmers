package com.yedam.app.user.mapper;

import java.util.List;

import com.yedam.app.user.vo.AttachVO;

public interface AttachMapper {
	// 전체조회
	public List<AttachVO> selectAttachList();
}
