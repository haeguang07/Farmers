package com.yedam.app.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.user.mapper.AdminMapper;
import com.yedam.app.user.vo.MemberVO;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper adminMapper;
	
	@Override
	public List<MemberVO> getMemberList() {
		return adminMapper.selectMemberList();
	}

}
