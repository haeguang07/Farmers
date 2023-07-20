package com.yedam.app.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.activity.service.BnbService;
import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.common.service.CodeService;
import com.yedam.app.common.service.ReviewService;
import com.yedam.app.common.vo.CodeVO;
import com.yedam.app.common.vo.ReviewVO;
import com.yedam.app.market.vo.PageVO;

@Controller
public class BnbController {

	@Autowired
	BnbService bnbService;
	@Autowired
	ReviewService revService;
	@Autowired
	CodeService codeService;

	// 전체조회
	@GetMapping("bnbList")
	public String getBnbList(Model model) {
		return "activity/bnb/bnbList";
	}

	// 전체조회- 조건별 정렬
	@GetMapping("bnbItem")
	@ResponseBody
	public Map<String, Object> getBnbList(@RequestParam(required = false) String region,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "최신순") String order) {
		System.out.println(order);
		List<BnbVO> list = bnbService.selectBnbList(region, page, order);
		int total = bnbService.selectCount(region);
		PageVO vo = new PageVO(page, total);

		Map<String, Object> map = new HashMap<>();
		map.put("bnb", list);
		map.put("page", vo);
		return map;
	}

	// 단건조회
	@GetMapping("bnbInfo")
	public String getBnbInfo(Model model, String boardNo) {
		model.addAttribute("bnb", bnbService.selectBnb(boardNo));
		return "activity/bnb/bnbInfo";
	}

	// 리뷰조회
	@ResponseBody
	@GetMapping("bnbInfo/review")
	public List<ReviewVO> getReview(String boardNo) {
		System.out.println(boardNo);
		return revService.getReview(boardNo);
	}

	// 리뷰등록
	@ResponseBody
	@PostMapping("bnbInfo/review")
	public ReviewVO addReview(ReviewVO vo) {
		revService.addReview(vo);
		System.out.println(vo);
		return vo;
	}

	// 등록 페이지
	@GetMapping("add/insertBnb")
	public String addBnbForm(Model model, BnbVO vo) {
		model.addAttribute("bnb", new BnbVO());
		return "activity/bnb/addBnbForm";
	}

	// 등록처리
	@PostMapping("add/insertBnb")
	public String addBnb(BnbVO vo) {
		System.out.println(vo);
		bnbService.insertBnb(vo);
		return "redirect:bnbList";
	}

	// 수정페이지
	@GetMapping("update/updateBnb")
	public String updateBnbForm(Model model, String boardNo) {
		BnbVO result = bnbService.selectBnb(boardNo);
		List<CodeVO> codeVo = codeService.getCodeList("0K");
		model.addAttribute("dst",codeVo);
		model.addAttribute("bnb",result);
		return "activity/bnb/updateBnbForm";
	}

	// 수정처리
	@PostMapping("update/updateBnb")
	public String updateBnb(BnbVO vo) {
		System.out.println(vo);
		int result = bnbService.updateBnb(vo);
		
		return "redirect:bnbInfo";
	}
	
	//삭제
	@PostMapping("deleteBnb")
	public String deleteBnb(String boardNo) {
		
		return "redirect:bnbList";
	}

}
