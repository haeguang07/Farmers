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

import com.yedam.app.market.service.CropsSaleService;
import com.yedam.app.market.vo.CropsSaleVO;
import com.yedam.app.market.vo.PageVO;

@Controller
public class CropsSaleController {
	
	@Autowired
	CropsSaleService csService;
	
	// 리스트 전체조회
	@GetMapping("cropsSaleList")
	public String getCropsSaleAllList(Model model) {
//		List<CropsSaleVO> list = csService.getCropsSaleList();
//		model.addAttribute("csList", list);
//		System.out.println(list);
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
//		System.out.println(pageNum);
//		System.out.println(category);
//		System.out.println(order);
//		System.out.println(search);
		
		PageVO vo = new PageVO(pageNum, total);
		Map<String, Object> map = new HashMap<>();
		map.put("csList", list);
		map.put("pageInfo", vo);
		
		return map;
	}
	
	// 단건조회
	@GetMapping("cropsSaleInfo")
	public String getCropsSaleInfo(CropsSaleVO csVO, Model model) {
//		System.out.println(csVO);
		CropsSaleVO info = csService.getCropsSaleInfo(csVO);
		System.out.println(info);
		model.addAttribute("csInfo", info);
		return "market/cropsSale/cropsSaleInfo";
	}
	
	// 등록 페이지 불러오기
	@GetMapping("cropsSaleInsert")
	public String insertCropsSaleForm() {
		return "market/cropsSale/cropsSaleInsert";
	}
	
	// 등록 기능
	@PostMapping("cropsSaleInsert")
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
	@GetMapping("cropsSaleUpdate")
	public String updateCropsSaleForm(CropsSaleVO csVO, Model model) {
//		System.out.println(csVO);
		CropsSaleVO find = csService.getCropsSaleInfo(csVO);
		System.out.println(find);
		model.addAttribute("csInfo", find);
		return "market/cropsSale/cropsSaleUpdate";
	}
	
	// 수정 기능
	@PostMapping("cropsSaleUpdate")
	@ResponseBody
	public Map<String, Object> updateCropsSale(CropsSaleVO csVO) {
		System.out.println(csVO);
		boolean result = false;
		String boardNo = csService.updateCropsSaleInfo(csVO);
		if(boardNo != null) {
			result = true;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("boardNo", boardNo);
		
		return map;
	}
	
	// 삭제 기능
	@PostMapping("cropsSaleDelete")
	@ResponseBody
	public String deleteCropsSale(String boardNo) {
		System.out.println(boardNo);
		csService.deleteCropsSaleInfo(boardNo);
		return "market/cropsSale/cropsSaleList";
	}
}
