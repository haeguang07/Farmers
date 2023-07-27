package com.yedam.app.user.service;

import java.util.List;

import com.yedam.app.common.vo.SearchVO;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;

public interface AdminService {
	//전체조회
	public List<MemberVO> getMemberList(SearchVO searchVO);
	//회원 상태 수정
	public void modifyMemberStts(List<MemberVO> list);
	//문의 조회
	public List<InquiryVO> getInqueryList(SearchVO vo);
	//문의 답변
	public boolean replyInquiry(InquiryVO vo);
	//농지대여 조회
	public List<FarmLendVO> getFarmLendList();
	

}
