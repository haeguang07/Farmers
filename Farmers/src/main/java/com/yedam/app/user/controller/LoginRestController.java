package com.yedam.app.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.user.service.EmailService;
import com.yedam.app.user.service.MemberService;
import com.yedam.app.user.vo.EmailVO;
import com.yedam.app.user.vo.MemberVO;

@RestController
public class LoginRestController {

	@Autowired
	MemberService memberService;
	@Autowired
	EmailService emailService;

	// 이메일 전송(인증 번호)
	@GetMapping("sendEmail")
	public Map<String, Object> email(String emailText) {
		int random = (int) (Math.random() * 100000) + 100000;
		String to = emailText;
		String title = "인증번호 발송";
		String subject = "<h3>안녕하세요</h3>\n <h4>인증번호는" + random + "입니다</h4>\n";
		EmailVO msg = new EmailVO(to, title, subject);
		emailService.sendMail(msg);
		Map<String, Object> map = new HashMap<>();
		map.put("random", random);
		return map;

	}

	// 이메일 전송(비밀번호 번호)
	@GetMapping("sendEmailPw")
	public String emailP(String emailText) {
		String random = getRamdom();
		String to = emailText;
		String title = "임시비밀번호 발송";
		String subject = "<h3>안녕하세요</h3>\n <h4>임시 비밀번호는" + random + "입니다</h4>\n" + "<h4>로그인후 변경해 주세요</h4>";
		EmailVO msg = new EmailVO(to, title, subject);
		emailService.sendMail(msg);
		return random;

	}
	//아이디 찾기
	@PostMapping("IDFound")
	public Map<String, String> IDFound(@RequestBody MemberVO member){
		Map<String, String> map = new HashMap<>();
		System.out.println(member);
		String id=memberService.idFound(member.getEmail());
		if(id==null) {
			map.put("retCode", "Fail");
		}else {
			map.put("retCode", "Success");
			map.put("id", id);
		}
		
		return map;
	}
	//비밀번호 찾기
	@PostMapping("pwFind")
	public Map<String, String> pwFind(@RequestBody MemberVO member){
		Map<String, String> map = new HashMap<>();
		System.out.println(member);
		String memNo=memberService.pwFound(member);
		if(memNo==null) {
			map.put("retCode", "Fail");
		}else {
			map.put("retCode", "Success");
			String pass=emailP(member.getEmail());
			BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
			String password = scpwd.encode(pass);
			member.setPw(password); member.setMemNo(memNo);
			System.out.println(member);
			memberService.pwChange(member);
		}
		
		return map;
	}
	
	
	
	// 아이디 중복
	@GetMapping("userIdCheck")
	public Map<String, String> idCheck(String uid){
		Map<String, String> map = new HashMap<>();
		if(memberService.idCheck(uid)) {
			map.put("retCode", "Success");
		}else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	//닉네임 중복
	@GetMapping("nickCheck")
	public Map<String, String> nickCheck(String nick){
		Map<String, String> map = new HashMap<>();
		if(memberService.nickCheck(nick)) {
			map.put("retCode", "Success");
		}else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	//회원가입
	@PostMapping("signup")
	public Map<String, Object> signup( MemberVO member){
		Map<String, Object> map = new HashMap<>();
		
		System.out.println(member);
		if(memberService.join(member)) {
			map.put("retCode", "Success");
		}else {
			map.put("retCode", "Fail");
		}
		
		return map;
	}
	/*
	 * //카카오
	 * 
	 * @GetMapping("kakao") public void kakao(OauthCodeVO vo) {
	 * System.out.println(vo);
	 * 
	 * }
	 */
	
	
	
	
	
	//램덤 13자리 문자열
	private String getRamdom() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 13;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) && (i <= 33 || i >= 38))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}
}
