package com.yedam.app.security;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.yedam.app.user.service.MemberService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {
	@Autowired
	MemberService memberService;
	@Autowired
	PrincipalOauth2UserService principalOauth2UserService;
	// 캐시
	private static final ClearSiteDataHeaderWriter.Directive[] SOURCE = { ClearSiteDataHeaderWriter.Directive.CACHE,
			ClearSiteDataHeaderWriter.Directive.COOKIES, ClearSiteDataHeaderWriter.Directive.STORAGE,
			ClearSiteDataHeaderWriter.Directive.EXECUTION_CONTEXTS };

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// 암호화
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 로그인 성공시
	@Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomSuccessHandler();
	}

	// 로그인 실패시
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
			System.out.println(session.getAttribute("acc"));
			if (accessDeniedException instanceof AccessDeniedException) {
				request.setAttribute("acc", "권한이 없습니다.");
			} else {
				session.removeAttribute("acc");
			}
		};
	}

	// 시큐리티
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				//경로 권한 설정
					.authorizeHttpRequests()
					.antMatchers("/add/**", "/update/**","/procedure/**").hasRole("ROLE_REGULAR")
					.antMatchers("/admin/**").hasRole("ROLE_ADMIN")
					.anyRequest().permitAll()
				//권한 없는 접근 처리
				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				//기본 로그인 설정
				.and().formLogin().loginPage("/login").passwordParameter("pw")
					.successHandler(authenticationSuccessHandler())
					.failureHandler(authenticationFailureHandler()).permitAll()
				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				//iframe위한 설정
	 		    .and().headers().frameOptions().sameOrigin() // 팝업창 띄우기
				.and()
				//로그아웃 설정
				.logout((logout) -> logout.logoutSuccessUrl("/").invalidateHttpSession(true)
					.addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(SOURCE)))
					.permitAll())
					.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				//api 로그인 설정
	 		    .and().oauth2Login().loginPage("/login") 
				.successHandler(authenticationSuccessHandler())
				.failureHandler(authenticationFailureHandler()) 
				.userInfoEndpoint().userService(principalOauth2UserService) // 사용자정보를 처리할 때 사용한다
		;

		return http.build();
	}

	// vue
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/static/").resourceChain(true)
				.addResolver(new PathResourceResolver() {
					@Override
					protected Resource getResource(String resourcePath, Resource location) throws IOException {
						Resource requestedResource = location.createRelative(resourcePath);
						return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
								: new ClassPathResource("/templates/user/admin/admin.html");
					}
				});
	}

}
