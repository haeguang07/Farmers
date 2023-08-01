package com.yedam.app.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.client.RestTemplate;

import com.yedam.app.user.service.MemberService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
	@Autowired
	MemberService memberService;
	@Autowired
	PrincipalOauth2UserService principalOauth2UserService;
	
	private static final ClearSiteDataHeaderWriter.Directive[] SOURCE = 
	 {ClearSiteDataHeaderWriter.Directive.CACHE, ClearSiteDataHeaderWriter.Directive.COOKIES, ClearSiteDataHeaderWriter.Directive.STORAGE, ClearSiteDataHeaderWriter.Directive.EXECUTION_CONTEXTS};
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomSuccessHandler();
	}
	@Bean
	AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomFailHandler();
	}
	
	   @Bean
	   public AccessDeniedHandler accessDeniedHandler() {
	       return (request, response, accessDeniedException) -> {
	           RequestCache requestCache = new HttpSessionRequestCache();
	           RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	           SavedRequest savedRequest = requestCache.getRequest(request, response);
	           HttpSession session = request.getSession();

	           String uri = "/";

	           System.out.println(request.getHeader("referer"));
	           if (request.getHeader("referer") != null && !request.getHeader("referer").isEmpty()) {
	               uri = request.getHeader("referer");
	           }

	           if (savedRequest != null) {
	               redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
	               requestCache.removeRequest(request, response);
	           } else {
	               redirectStrategy.sendRedirect(request, response, uri);
	           }

	           if (accessDeniedException instanceof AccessDeniedException) {
	               session.setAttribute("noAccess", "접근이 거부되었습니다. 권한이 없습니다.");
	           } else {
	               session.removeAttribute("noAccess"); 
	           }
	       };
	   }
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/**").permitAll()
				.antMatchers("/add/**", "/update/**").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			.and()
				 .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
			.and()
				.headers()
				.frameOptions()
				.disable()
			.and()
			.formLogin()
				.loginPage("/login")
				.passwordParameter("pw")
				.successHandler(authenticationSuccessHandler())
				.failureHandler(authenticationFailureHandler())
				.permitAll()
			.and()
				.headers().frameOptions().sameOrigin() //팝업창 띄우기
			.and()
			.logout((logout) -> logout
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
					.addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(SOURCE)))
					.permitAll())
			 	.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
			.and()
			.oauth2Login()				// OAuth2기반의 로그인인 경우
            .loginPage("/login")		// 인증이 필요한 URL에 접근하면 /loginForm으로 이동
            .successHandler(authenticationSuccessHandler())		// 로그인 성공하면 "/" 으로 이동
            .failureHandler(authenticationFailureHandler())		// 로그인 실패 시 /loginForm으로 이동
            .userInfoEndpoint()			// 로그인 성공 후 사용자정보를 가져온다
            .userService(principalOauth2UserService)	//사용자정보를 처리할 때 사용한다
        

;
			
	return http.build();
	}	
	
	
	
}
