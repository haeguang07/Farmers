package com.yedam.app.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.user.service.EmailService;
import com.yedam.app.user.service.MemberService;
import com.yedam.app.user.vo.EmailMessage;
import com.yedam.app.user.vo.MemberVO;

@RestController
public class LoginRestController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	EmailService emailService;
	
	//로그인 기능
	@PostMapping("login")
	public Map<String, Object> login(MemberVO vo, HttpServletRequest request){
		
		vo=memberService.login(vo);
		Map<String, Object> map= new HashMap<>();
		if(vo.getMemNo()!=null) {
			map.put("result", "success");
			HttpSession session = request.getSession();
			session.setAttribute("mem", vo);
		}else {
			map.put("result", "fail");
		}
		return map;
	}
	//이메일 전송(인증 번호)
	@GetMapping("sendEmail")
	public Map<String,Object> email() {
		int random = (int) (Math.random() * 100000) + 100000;
		String to ="hk97564@naver.com";
		String title="인증번호 발송";
		String subject="<h3>안녕하세요</h3>\n <h4>인증번호는" +random +"입니다</h4>\n";
		EmailMessage msg = new EmailMessage(to,title,subject);
		emailService.sendMail(msg);
		Map<String, Object> map = new HashMap<>();
		map.put("random", random);
		return map;
		
	}
	//이메일 전송(인증 번호)
		@GetMapping("sendEmailPw")
		public String emailP() {
			String random = getRamdom();
			String to ="hk97564@naver.com";
			String title="임시비밀번호 발송";
			String subject="<h3>안녕하세요</h3>\n <h4>임시 비밀번호는" +random +"입니다</h4>\n"+ "<h4>로그인후 변경해 주세요</h4>";
			EmailMessage msg = new EmailMessage(to,title,subject);
			emailService.sendMail(msg);
			return "success";
			
		}
		
	private String getRamdom() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 13;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) &&(i <= 33 || i >= 38)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}
}
