package com.yedam.app.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.user.mapper.MyPageMapper;
import com.yedam.app.user.vo.MemberVO;

@Service
public class MyPageServiceImpl implements MyPageService {
	@Autowired
	MyPageMapper myPageMapper;
	
	@Override
	public MemberVO checkPassword(String memNo) {
		return myPageMapper.checkPassword(memNo);
	}

	@Override
	public MemberVO getMemberInfo(String memNo) {
		return myPageMapper.getMemberInfo(memNo);
	}

}
