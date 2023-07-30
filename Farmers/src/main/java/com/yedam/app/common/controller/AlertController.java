package com.yedam.app.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.common.service.AlertService;
import com.yedam.app.user.vo.AlertVO;
import com.yedam.app.user.vo.MemberVO;

@Controller
public class AlertController {
	
	@Autowired
	AlertService alertService;
	
	@GetMapping("sendAlert")
	@ResponseBody
	public boolean sendAlert(AlertVO vo) {
		return alertService.addAlert(vo);
	}
	
	
}
