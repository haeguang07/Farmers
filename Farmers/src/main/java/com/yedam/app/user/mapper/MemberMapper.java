package com.yedam.app.user.mapper;

import java.util.List;

import com.yedam.app.user.vo.MemberVO;

public interface MemberMapper {
	//회원 조회
	public MemberVO selectMember(String id);
	public List<String> selectRole(String codeNo);
}
