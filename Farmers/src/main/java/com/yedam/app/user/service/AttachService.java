package com.yedam.app.user.service;

import java.util.List;

import com.yedam.app.user.vo.AttachVO;

public interface AttachService {
	// 전체조회
	public List<AttachVO> getAttachList(String boardNo);
}
