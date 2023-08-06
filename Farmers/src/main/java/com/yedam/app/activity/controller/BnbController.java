package com.yedam.app.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasypt.encryption.StringEncryptor;
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
import com.yedam.app.common.service.PaymentService;
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
	@Autowired
	PaymentService payService;
	@Autowired
	StringEncryptor jasyptStringEncryptor;

	// 전체조회
	@GetMapping("/bnbList")
	public String getBnbList(Model model) {
		return "activity/bnb/bnbList";
	}

	// 전체조회- 조건별 정렬
	@GetMapping("bnbItem")
	@ResponseBody
	public Map<String, Object> getBnbList(@RequestParam(required = false) String region,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "최신순") String order) {
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
		BnbVO vo = bnbService.selectBnb(boardNo);
		vo.setMemZip(jasyptStringEncryptor.decrypt(vo.getMemZip()));
		vo.setMemAdr(jasyptStringEncryptor.decrypt(vo.getMemAdr()));
		vo.setMemAdrdeta(jasyptStringEncryptor.decrypt(vo.getMemAdrdeta()));
		vo.setEmail(jasyptStringEncryptor.decrypt(vo.getEmail()));
		vo.setMbl(jasyptStringEncryptor.decrypt(vo.getMbl()));
		model.addAttribute("bnb", vo);
		model.addAttribute("rev", revService.getCount(boardNo));
		model.addAttribute("avg", revService.getAverage(boardNo));
		List<String> list = payService.getPayMember(boardNo);
		model.addAttribute("pay", list);
		return "activity/bnb/bnbInfo";
	}

	// 리뷰조회
	@ResponseBody
	@GetMapping("rev/review")
	public List<ReviewVO> getReview(String boardNo) {
		return revService.getReview(boardNo);
	}

	// 리뷰등록
	@ResponseBody
	@PostMapping("rev/review")
	public ReviewVO addReview(ReviewVO vo) {
		revService.addReview(vo);
		return vo;
	}

	// 리뷰삭제
	@ResponseBody
	@GetMapping("rev/revDel")
	public String delReview(String revNo) {
		int result = revService.delReview(revNo);
		if (result > 0) {
			return "delete success!";
		} else {
			return "fail";
		}
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
		bnbService.insertBnb(vo);
		return "redirect:/bnbList";
	}

	// 수정페이지
	@GetMapping("update/updateBnb")
	public String updateBnbForm(Model model, String boardNo) {
		BnbVO result = bnbService.selectBnb(boardNo);
		List<CodeVO> codeVo = codeService.getCodeList("0K");
		model.addAttribute("dst", codeVo);
		model.addAttribute("bnb", result);
		return "activity/bnb/updateBnbForm";
	}

	// 수정처리
	@PostMapping("update/updateBnb")
	@ResponseBody
	public String updateBnb(BnbVO vo) {
		System.out.println(vo);
		int result = bnbService.updateBnb(vo);
		if (result > 0) {
			return "수정 완료";
		} else {
			return "수정 실패";
		}
	}

	// 삭제
	@GetMapping("deleteBnb")
	public String deleteBnb(String boardNo) {
		bnbService.deleteBnb(boardNo);
		return "redirect:bnbList";
	}

	// 예약 날짜 확인
	@GetMapping("checkRsvDate")
	@ResponseBody
	public List<String> checkRsvDate(String boardNo) {
		return bnbService.rsvDateCheck(boardNo);
	}

}
