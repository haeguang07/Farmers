package com.yedam.app.board.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

//지원사업 컨트롤러
@Controller
public class EduController {

	// 목록 페이지
	@GetMapping("/eduList")
	public String eduList(Model model) {
		return "board/edu/eduList";
	}

	// 상세 페이지
	@GetMapping("/eduInfo")
	public String eduInfo(String seq, Model model) {
		RestTemplate temp = new RestTemplate();
		String uri = "https://apis.data.go.kr/1390000/youngV2/policyViewV2?typeDv=json&serviceKey=XMbKkFwk066V9gZ9uvn63yI7xGN5wEbCjEffJl0upWWgKhcaFHesiDZHkYOwrOpgeyC/YwxBqavNpAhMdAdU5w==&seq="
				+ seq;
		Map<String, Object> map = temp.getForObject(uri, Map.class);
		Map<String, Object> map2 = (Map<String, Object>) map.get("policy_result");
		model.addAttribute("edu", map2);
		return "board/edu/eduInfo";
	}

	// 귀농귀촌 가이드
	@GetMapping("/guide")
	public String guide(Model model) {
		return "board/edu/guide";
	}

}
