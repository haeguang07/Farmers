package com.yedam.app.user.mapper;

import com.yedam.app.user.vo.MemberVO;

public interface MyPageMapper {
	//비밀번호 확인
	public MemberVO checkPassword(String memNo);
	
	//회원정보 가져오기
	public MemberVO getMemberInfo(String memNo);

}
