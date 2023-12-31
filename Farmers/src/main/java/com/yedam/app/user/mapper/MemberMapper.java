package com.yedam.app.user.mapper;

import java.util.List;

import com.yedam.app.user.vo.MemberVO;

public interface MemberMapper {
	// 회원 조회
	public MemberVO selectMember(String id);
	public MemberVO selectMemberByNo(String memNo);
	//이메일 중복용 
	public List<MemberVO> selectEmail();

	// 아이디중복
	public String selectId(String id);

	// 닉네임중복
	public String selectNick(String nick);
	// 회원 가입
	public int insertMember(MemberVO memberVO);

	public int insertMemberDetail(MemberVO memberVO);

	// 아이디찾기
	public String selectID(String email);
	// 비밀번호찾기
	public String selectPw(MemberVO vo);
	//비밀번호 변경(memNo와 변경후 pw 필요)
	public int updatePw(MemberVO vo);

}
