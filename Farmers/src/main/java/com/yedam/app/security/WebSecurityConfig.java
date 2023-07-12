package com.yedam.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
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
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.passwordParameter("pw")
			.successHandler(authenticationSuccessHandler())
			.failureHandler(authenticationFailureHandler())
			.permitAll()
			.and()
			.logout((logout) -> logout.permitAll());
			
	return http.build();
	}	
	
}
