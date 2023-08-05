package com.yedam.app.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yedam.app.activity.vo.BnbVO;
import com.yedam.app.activity.vo.ExpVO;
import com.yedam.app.common.service.AlertService;
import com.yedam.app.common.service.CodeService;
import com.yedam.app.common.vo.CodeVO;
import com.yedam.app.farm.vo.FarmLendVO;
import com.yedam.app.market.vo.AuctionVO;
import com.yedam.app.market.vo.FundingVO;
import com.yedam.app.market.vo.MarketVO;
import com.yedam.app.user.service.AdminService;
import com.yedam.app.user.vo.AdminSearchVO;
import com.yedam.app.user.vo.AlertVO;
import com.yedam.app.user.vo.InquiryVO;
import com.yedam.app.user.vo.MemberVO;
import com.yedam.app.user.vo.UpdateSttsVO;

@RestController
public class AdminRestController {
	@Autowired
	AdminService adminService;
	@Autowired
	CodeService codeService;
	@Autowired
	AlertService alertService;

	// 관리자 페이지
	@GetMapping("/admin")
	public ModelAndView memberManagement() {
		ModelAndView mv = new ModelAndView("user/admin/admin");
		return mv;
	}

	// Vuex 공통 사용할 코드들(지역1,2,승인/거부)
	@GetMapping("admin/getCodes")
	public Map<String, List<CodeVO>> getPublicCode() {
		Map<String, List<CodeVO>> code = codeService.getCodes("0K", "0E", "k0", "k1", "k2", "k3", "k4", "k5", "k6",
				"k7", "k8","0Y","0Z","0C","0F");
		List<CodeVO> list2 = code.get("0E");
		int[] arr = { 7, 6, 5, 4, 3, 2, 0 };
		for (int i = 0; i < arr.length; i++) {
			list2.remove(arr[i]);
		}
		code.put("0E", list2);
		return code;
	}

	// 회원조회
	@GetMapping("/admin/members")
	public Map<String, Object> memberList(AdminSearchVO searchVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MemberVO> list = adminService.getMemberList(searchVO);
		Map<String, List<CodeVO>> codeMap = codeService.getCodes("0B", "0C");
		map.put("memberList", list);
		map.put("code", codeMap);
		return map;
	}

	// 회원수정
	@PutMapping("/admin/members/update")
	public List<MemberVO> memberUpdate(@RequestBody List<MemberVO> list) {
		return adminService.modifyMemberStts(list);
	}

	// 문의 조회
	@GetMapping("/admin/inquiryAdmin")
	public List<InquiryVO> inquiryAdmin(AdminSearchVO searchVO) {
		return adminService.getInqueryList(searchVO);
	}

	// 문의 답변
	@PutMapping("admin/inquiryReply")
	public Map<String, String> inquiryReply(@RequestBody InquiryVO vo) {
		Map<String, String> map = new HashMap<>();
		if (adminService.replyInquiry(vo)) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}
		return map;
	}

	// 승인거부 알림
	@PostMapping("admin/rejectAlert")
	public Map<String, String> rejectAlert(@RequestBody AlertVO vo) {
		Map<String, String> map = new HashMap<>();
		if (alertService.addAlert(vo)) {
			System.out.println(vo);
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}
		return map;
	}

	// 농지대여
	@GetMapping("admin/farms")
	public List<FarmLendVO> getFarms(AdminSearchVO vo) {
		return adminService.getFarmLendList(vo);
	}

	// 마켓
	@GetMapping("admin/markets")
	public List<MarketVO> getMarkets(AdminSearchVO vo) {
		return adminService.getMarketList(vo);
	}

	// 비엔비
	@GetMapping("admin/bnbs")
	public List<BnbVO> getBnbs(AdminSearchVO vo) {
		return adminService.getFarmBnbList(vo);
	}

	// 경매장
	@GetMapping("admin/auctions")
	public List<AuctionVO> getAuctions(AdminSearchVO vo) {
		return adminService.getAuctionList(vo);
	}

	// 펀딩
	@GetMapping("admin/fundings")
	public List<FundingVO> getFundings(AdminSearchVO vo) {
		return adminService.getFundingList(vo);
	}
	// 펀딩
	@GetMapping("admin/exps")
	public List<ExpVO> getExps(AdminSearchVO vo) {
		return adminService.getExpList(vo);
	}


	// 동적으로 승인/거부
	@PutMapping("admin/chageRegStatus")
	public Map<String, Object> chageRegStatus(@RequestBody List<UpdateSttsVO> list) {
		return adminService.chageStatus(list);
	}

}
