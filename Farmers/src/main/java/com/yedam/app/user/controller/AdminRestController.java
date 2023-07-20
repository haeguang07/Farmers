package com.yedam.app.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.user.service.AdminService;
import com.yedam.app.user.vo.MemberVO;
@RestController
@RequestMapping("admin/")
public class AdminRestController {
	@Autowired
	AdminService adminService;
	
	@GetMapping("memberList")
	public Map<String, Object> memberList(){
		Map<String, Object> map = new HashMap<>();
		List<MemberVO> list = adminService.getMemberList();
		map.put("memList", list);
		return map;
	}
	
}
