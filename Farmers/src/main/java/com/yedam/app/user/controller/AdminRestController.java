package com.yedam.app.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yedam.app.common.service.CodeService;
import com.yedam.app.common.vo.CodeVO;
import com.yedam.app.common.vo.SearchVO;
import com.yedam.app.user.service.AdminService;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
@RestController
public class AdminRestController {
	@Autowired
	AdminService adminService;
	@Autowired
	CodeService codeService;
	
	//관리자 페이지
	@GetMapping("/admin")
	public ModelAndView memberManagement() {
		ModelAndView mv = new ModelAndView("user/admin/admin");
		return mv;
	}
	
	@GetMapping("/admin/members")
	public Map<String, Object> memberList(SearchVO searchVO){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<MemberVO> list = adminService.getMemberList(searchVO);
		Map<String, List<CodeVO>> codeMap=codeService.getCodes("0B","0C");
		map.put("memberList", list);
		map.put("code", codeMap);
		return map;
	}
	
	@GetMapping("/admin/inquiryAdmin")
	public List<InquiryVO> inquiryAdmin(SearchVO searchVO){
		 return adminService.getInqueryList(searchVO);
	}
	@PutMapping("/admin/members/update")
	public List<MemberVO> memberUpdate(@RequestBody List<MemberVO> list){
		System.out.println(list);
		adminService.modifyMemberStts(list);
		SearchVO vo= new SearchVO();  
		return (List<MemberVO>) memberList(vo).get("memberList");
	}
	
}
