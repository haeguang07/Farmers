package com.yedam.app.user.service;

import java.util.List;

import com.yedam.app.common.vo.SearchVO;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;

public interface AdminService {
	//전체조회
	public List<MemberVO> getMemberList(SearchVO searchVO);
	//문의 조회
	public List<InquiryVO> getInqueryList(SearchVO vo);
	

}
