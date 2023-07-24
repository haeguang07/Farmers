package com.yedam.app.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yedam.app.common.vo.SearchVO;
import com.yedam.app.user.service.AdminService;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
@RestController
public class AdminRestController {
	@Autowired
	AdminService adminService;
	
	//관리자 페이지
	@GetMapping("/admin")
	public ModelAndView memberManagement() {
		ModelAndView mv = new ModelAndView("user/admin/admin");
		return mv;
	}
	
	@GetMapping("/admin/members")
	public List<MemberVO> memberList(SearchVO searchVO){
		List<MemberVO> list = adminService.getMemberList(searchVO);
		return list;
	}
	
	@GetMapping("/admin/inquiryAdmin")
	public List<InquiryVO> inquiryAdmin(SearchVO searchVO){
		 return adminService.getInqueryList(searchVO);
	}
	@PutMapping("/admin/members/update")
	public List<MemberVO> memberUpdate(@RequestBody List<MemberVO> list){
		adminService.modifyMemberStts(list);
		SearchVO vo= new SearchVO();  
		return memberList(vo);
	}
	
}
