package com.yedam.app.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.activity.service.SkilledService;
import com.yedam.app.activity.vo.SkilledVO;
import com.yedam.app.common.service.CodeService;
import com.yedam.app.market.vo.PageVO;

@Controller
public class SkilledController {

	@Autowired
	SkilledService skilledService;
	
	@Autowired
	CodeService codeService;
	
	// 금손귀농인 메인 페이지 리턴
	@GetMapping("skilled")
	public String skilledExpert(Model model){
		model.addAttribute("dst1", codeService.getCodeList("0K"));
		return "activity/skilled/skilledList";
	}
	
	// 전체 리스트 조회
	@GetMapping("skilledList")
	@ResponseBody
	public Map<String, Object> skilledList(@RequestParam(required = false) String div,
			@RequestParam(required = false, defaultValue = "0") int pageNum,
			@RequestParam(required = false) String dst1, 
			@RequestParam(required = false) String dst2) {

		pageNum = (pageNum == 0 ? 1 : pageNum);
		List<SkilledVO> list = skilledService.skilledList(div, pageNum, dst1, dst2);
		int total = skilledService.listCount(div);
		PageVO vo = new PageVO(pageNum, total);

		Map<String, Object> map = new HashMap<>();
		map.put("gold", list);
		map.put("page", vo);
		if (dst1 != null) {
			map.put("dst2", codeService.getCodeList(dst1));
		}
		
		return map;
	}
	
	// 등록 모달창 이동
	@GetMapping("add/skilled")
	public String skilledInfo() {
		return "activity/skilled/insertSkilled";
	}
	
	
	
}
