package com.yedam.app.market.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.yedam.app.market.service.FundingService;
import com.yedam.app.market.vo.FundingVO;
import com.yedam.app.market.vo.PageVO;

@Controller
public class FundingController {
	@Autowired
	FundingService fundingService;

	// 기본 리스트 출력
//	@GetMapping("fundingList")
//	public String fundingList(Model model) {
//		List<FundingVO> list = fundingService.getFundingList();
//		model.addAttribute("fundingList", list);
//		System.out.println(list);
//		return "market/funding/fundingList";
//	}

	// 펀딩 리스트 폼
	@GetMapping("fundingList")
	public String fundingList(Model model) {
		return "market/funding/fundingList";
	}

	// 페이징 pageVO 방식
	@PostMapping("fundingList")
	@ResponseBody
	public Map<String, Object> fundingListPage(@RequestParam(required = false, defaultValue = "0") int pageNum,
			@RequestParam(required = false) String category,
			@RequestParam(required = false, defaultValue = "최신순") String order,
			@RequestParam(required = false) String search, Model model) {

		pageNum = (pageNum == 0 ? 1 : pageNum);
		int total = fundingService.fundingTotal(category, search);
		List<FundingVO> list = fundingService.getFundingListPage(pageNum, category, order, search);

		PageVO vo = new PageVO(pageNum, total);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fundingList", list);
		map.put("pageInfo", vo);

		return map;
	}

	// 펀딩 상세 페이지
	@GetMapping("fundingInfo")
	public String fundingInfo(FundingVO vo, Model model) {
		FundingVO info = fundingService.getFundingInfo(vo);
		List<FundingVO> poplu = fundingService.getPolpularFnd();
		model.addAttribute("fundingInfo", info);
		model.addAttribute("polurInfo", poplu);
		return "market/funding/fundingInfo";
	}

	// 펀딩 등록 페이지
	@GetMapping("addFunding")
	public String addFundingForm() {
		return "market/funding/addFundingForm";
	}

	// 펀딩 등록 기능
	@PostMapping("addFunding")
	@ResponseBody
	public String addFunding(FundingVO formData) {
		boolean result = fundingService.insertFunding(formData);

		if (result) {
			return "success";
		} else {
			return "fail";
		}
	}

	// 펀딩 수정 페이지
	@GetMapping("modifyFunding")
	public String modifyFundingForm(FundingVO vo, Model model) {
		FundingVO result = fundingService.getFundingInfo(vo);
		model.addAttribute("fundingInfo", result);
		return "market/funding/modifyFundingForm";
	}

	// 펀딩 수정 기능
	@PostMapping("modifyFunding")
	@ResponseBody
	public String modifyFunding(FundingVO vo, Model model) {
		System.out.println("yes");
		System.out.println(vo);
		boolean result = fundingService.modifyFunding(vo);

		if (result) {
			return "success";
		} else {
			return "fail";
		}
	}

	// ckEditor 이미지 등록
	@Value("${file.upload.path}") // 프로퍼티 혹은 빈에 있는 값들을 들고올 때 사용 (Spring value로 import)
	public String uploadPath;

	@PostMapping(value = "/image/upload")
	public ModelAndView image(MultipartHttpServletRequest request) {
		// modelandview를 사용하여 json 형식으로 보내기위해 모델앤뷰 생성자 매개변수로 jsonView 라고 써줌
		// jsonView 라고 쓴다고 무조건 json 형식으로 가는건 아니고 @Configuration 어노테이션을 단
		// WebConfig 파일에 MappingJackson2JsonView 객체를 리턴하는 jsonView 매서드를 만들어서 bean으로
		// 등록해야함
		ModelAndView mav = new ModelAndView("jsonView");

		// ckeditor 에서 파일을 보낼 때 upload : [파일] 형식으로 해서 넘어오기 때문에 upload라는 키의 밸류를 받아서
		// uploadFile에 저장함
		MultipartFile uploadFile = request.getFile("upload");

		// 업로드 파일의 본래 이름
		String originalName = uploadFile.getOriginalFilename();

		// 업로드 파일의 확장자 이름
		String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);

		// 날짜 폴더 생성
		String folderPath = makeFolder();

		// UUID (겹치는 파일 이름 처리)
		String uuid = UUID.randomUUID().toString();

		// 저장할 파일 이름 중간에 "_"를 이용하여 구분
		String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;

		String saveName = uploadPath + File.separator + uploadFileName;

		Path savePath = Paths.get(saveName);

		// 불러올 때 사용할 경로
		String loadPath = "/images/" + File.separator + uploadFileName;

		// Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
		System.out.println("path : " + saveName);
		try {
			uploadFile.transferTo(savePath);
			// uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
		} catch (IOException e) {
			e.printStackTrace();
		}

		// DB에 해당 경로 저장
		// 1) 사용자가 업로드 할 때 사용한 파일명
		// 2) 실제 서버에 업로드할 때 사용한 경로
		// 1,2 둘다 DB에 저장해야함

		// DB에 저장할 때 java에서만 읽히는 File.separator를 /로 변환 후 DB에 저장
		String imagePath = "/images/" + uploadFileName.replace(File.separator, "/");

		System.out.println(saveName);
		System.out.println(savePath);

		// ckeditor는 이미지 업로드 후 이미지 표시하기 위해 uploaded 와 url을 json 형식으로 받아야 함
		// uploaded, url 값을 modelandview를 통해 보냄
		mav.addObject("uploaded", true); // 업로드 완료
		mav.addObject("url", loadPath); // 업로드 파일의 경로

		return mav;
	}

	// 일반 첨부파일 저장
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public Map<String, String> uploadFile(@RequestPart MultipartFile[] uploadFiles, Model model) { // @RequestPart 첨부파일
																									// 사용 시 사용 (주로
		// 배열 사용)
		Map<String, String> map = new HashMap<String, String>();

		for (MultipartFile uploadFile : uploadFiles) {

			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);

			System.out.println("fileName : " + fileName);

			// 날짜 폴더 생성
			String folderPath = makeFolder();
			// UUID
			String uuid = UUID.randomUUID().toString();
			// 저장할 파일 이름 중간에 "_"를 이용하여 구분

			String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;

			String saveName = uploadPath + File.separator + uploadFileName;

			Path savePath = Paths.get(saveName);

			String loadPath = "/images/" + File.separator + uploadFileName;
			// Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
			System.out.println("path : " + saveName);
			try {
				uploadFile.transferTo(savePath);
				// uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
			} catch (IOException e) {
				e.printStackTrace();
			}

			// DB에 해당 경로 저장
			// 1) 사용자가 업로드 할 때 사용한 파일명
			// 2) 실제 서버에 업로드할 때 사용한 경로
			// 1,2 둘다 DB에 저장해야함

			// DB에 저장할 때 java에서만 읽히는 File.separator를 /로 변환 후 DB에 저장
			String imagePath = "/images/" + uploadFileName.replace(File.separator, "/");
			System.out.println(uploadFileName);
			System.out.println(imagePath);

			map.put("loadPath", loadPath);
			map.put("dbPath", imagePath);
		}
		return map;
	}

	// 이미지 저장 폴더 생성
	private String makeFolder() {

		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator); // File.separator = 자바가 인식하는 경로 구분 형식 (/ ->
																// File.separator)
		File uploadPathFoler = new File(uploadPath, folderPath); // uploadPath 경로 / folderPath 폴더명
		// File newFile= new File(dir,"파일명");

		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}

}
