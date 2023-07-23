package com.yedam.app.user.mapper;

import java.util.List;

import com.yedam.app.common.vo.SearchVO;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;

public interface AdminMapper {
	//전체회원 조회
	public List<MemberVO> selectMemberList(SearchVO vo);
	
	//문의 조회
	public List<InquiryVO> selectInqueryList(SearchVO vo);
}
