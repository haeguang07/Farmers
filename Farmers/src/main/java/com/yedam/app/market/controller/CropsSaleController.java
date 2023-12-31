package com.yedam.app.market.controller;

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

import com.yedam.app.common.service.CodeService;
import com.yedam.app.common.service.ReviewService;
import com.yedam.app.common.vo.ReviewVO;
import com.yedam.app.market.service.CropsSaleService;
import com.yedam.app.market.vo.CropsSaleVO;
import com.yedam.app.market.vo.PageVO;

// 김승환 - 농작물 판매 관리
@Controller
public class CropsSaleController {
	
	@Autowired
	CropsSaleService csService;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	ReviewService revService;
	
	// 리스트 전체조회
	@GetMapping("cropsSaleList")
	public String getCropsSaleAllList() {
		return "market/cropsSale/cropsSaleList";
	}
	
	// 리스트 전체조회(페이징)
	@PostMapping("cropsSaleList")
	@ResponseBody
	public Map<String, Object> getCropsSaleListPage(@RequestParam(required = false, defaultValue = "0") int pageNum,
													@RequestParam(required = false) String category,
													@RequestParam(required = false, defaultValue = "최신순") String order,
													@RequestParam(required = false) String search) {
		pageNum = pageNum == 0 ? 1 : pageNum;
		int total = csService.getCount(category, search);
		List<CropsSaleVO> list = csService.getCropsSaleListPage(pageNum, category, order, search);
		
		PageVO vo = new PageVO(pageNum, total);
		Map<String, Object> map = new HashMap<>();
		map.put("csList", list);
		map.put("pageInfo", vo);
		
		return map;
	}
	
	// 단건조회
	@GetMapping("cropsSaleInfo")
	public String getCropsSaleInfo(CropsSaleVO csVO, String boardNo, Model model) {
		CropsSaleVO info = csService.getCropsSaleInfo(csVO);
		model.addAttribute("csInfo", info);
		model.addAttribute("codeInfo", codeService.getCodeList("0N"));
		model.addAttribute("reviewCount", csService.totalRate(boardNo));
		return "market/cropsSale/cropsSaleInfo";
	}
	
	// 등록 페이지 불러오기
	@GetMapping("add/cropsSaleInsert")
	public String insertCropsSaleForm() {
		return "market/cropsSale/cropsSaleInsert";
	}
	
	// 등록 기능
	@PostMapping("add/cropsSaleInsert")
	@ResponseBody
	public String insertCropsSale(CropsSaleVO csVO) {
		boolean result = csService.insertCropsSaleInfo(csVO);
		
		if(result) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 수정 페이지 불러오기
	@GetMapping("update/cropsSaleUpdate")
	public String updateCropsSaleForm(CropsSaleVO csVO, Model model) {
		CropsSaleVO find = csService.getCropsSaleInfo(csVO);
		model.addAttribute("csInfo", find);
		return "market/cropsSale/cropsSaleUpdate";
	}
	
	// 수정 기능
	@PostMapping("update/cropsSaleUpdate")
	@ResponseBody
	public Map<String, Object> updateCropsSale(CropsSaleVO csVO) {
		boolean result = false;
		String boardNo = csService.updateCropsSaleInfo(csVO);
		if(boardNo != null) {
			result = true;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	
	// 삭제 기능
	@PostMapping("cropsSaleDelete")
	@ResponseBody
	public String deleteCropsSale(String boardNo) {
		csService.deleteCropsSaleInfo(boardNo);
		return "success";
	}
	
	// 리뷰 리스트 조회
	@GetMapping("cropsSaleInfo/review")
	@ResponseBody
	public List<ReviewVO> getReview(String boardNo) {
		return revService.getReview(boardNo);
	}
	
	// 리뷰 등록 기능
	@PostMapping("cropsSaleInfo/reviewInsert")
	@ResponseBody
	public ReviewVO addReview(ReviewVO reviewVO) {
		revService.addReview(reviewVO);
		return reviewVO;
	}
	
	// 리뷰 삭제 기능
	@PostMapping("cropsSaleInfo/reviewDelete")
	@ResponseBody
	public String delReview(String revNo) {
		int result = revService.delReview(revNo);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}
