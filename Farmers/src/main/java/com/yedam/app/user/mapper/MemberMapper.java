package com.yedam.app.user.mapper;

import com.yedam.app.user.vo.MemberVO;

public interface MemberMapper {
	//회원 조회
	public MemberVO selectMember(String id);
	//아이디중복
	public String selectId(String id);
	//닉네임중복
	public String selectNick(String nick);
}
