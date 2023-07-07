package com.yedam.app.user.mapper;

import com.yedam.app.user.vo.MemberVO;

public interface MemberMapper {
	//회원 조회
	public MemberVO selectMember(MemberVO vo);
}
