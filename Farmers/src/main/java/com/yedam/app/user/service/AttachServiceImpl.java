package com.yedam.app.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.user.mapper.AttachMapper;
import com.yedam.app.user.vo.AttachVO;

@Service
public class AttachServiceImpl implements AttachService {
	
	@Autowired
	AttachMapper attachMapper;
	
	@Override
	public List<AttachVO> getAttachList() {
		return attachMapper.selectAttachList();
	}

}
