package com.yedam.app.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.user.mapper.MemberMapper;
import com.yedam.app.user.vo.MemberVO;
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper memberMapper;
	
	
	@Override
	public MemberVO login(MemberVO vo) {
		
		return memberMapper.selectMember(vo);
	}
	
}
