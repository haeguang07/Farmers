package com.yedam.app.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.common.service.AlertService;
import com.yedam.app.user.vo.AlertVO;

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
