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

import com.yedam.app.common.service.ReviewService;
import com.yedam.app.common.vo.ReviewVO;
import com.yedam.app.market.service.CartService;
import com.yedam.app.market.service.MarketService;
import com.yedam.app.market.vo.CartVO;
import com.yedam.app.market.vo.MarketVO;
import com.yedam.app.market.vo.PageVO;

@Controller
public class MarketController {

	@Autowired
	MarketService mkService;
	@Autowired
	ReviewService revService;
	@Autowired
	CartService cartService;

	// 전체조회 페이지
	@GetMapping("marketList")
	public String marketList(Model model) {
		return "market/market/marketList";
	}

	// 전체조회-조건별 정렬
	@GetMapping("marketItem")
	@ResponseBody
	public Map<String, Object> marketList(@RequestParam(required = false) String mktCtg,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "최신순") String order) {

		List<MarketVO> list = mkService.getMarketList(mktCtg, page, order);
		int total = mkService.selectCount(mktCtg);
		PageVO vo = new PageVO(page, total);

		Map<String, Object> map = new HashMap<>();
		map.put("mk", list);
		map.put("page", vo);
		return map;
	}

	// 단건조회
	@GetMapping("mkInfo")
	public String getMkInfo(Model model, String boardNo) {
		model.addAttribute("mk", mkService.getMarketInfo(boardNo));
		model.addAttribute("rev", revService.getCount(boardNo));
		return "market/market/marketInfo";
	}

	// 리뷰조회
	@ResponseBody
	@GetMapping("mk/review")
	public List<ReviewVO> getReview(String boardNo) {
		System.out.println(boardNo);
		return revService.getReview(boardNo);
	}

	// 리뷰등록
	@ResponseBody
	@PostMapping("mk/review")
	public ReviewVO addReview(ReviewVO vo) {
		revService.addReview(vo);
		System.out.println(vo);
		return vo;
	}

	// 리뷰삭제
	@ResponseBody
	@GetMapping("mk/revDel")
	public String delReview(String revNo) {
		System.out.println(revNo);
		int result = revService.delReview(revNo);
		if (result > 0) {
			return "delete!";
		} else {
			return "fail";
		}
	}

	// 등록 페이지
	@GetMapping("add/insertMk")
	public String addMkForm(Model model, MarketVO vo) {
		model.addAttribute("mk", new MarketVO());
		return "market/market/addMkForm";
	}

	// 등록처리
	@PostMapping("add/insertMk")
	public String addMk(MarketVO vo) {
		System.out.println(vo);
		mkService.insertMk(vo);
		return "redirect:/marketList";
	}

	// 삭제
	@GetMapping("deleteMk")
	public String deleteMk(String boardNo) {
		mkService.deleteMk(boardNo);
		return "redirect:marketList";
	}

	
}
