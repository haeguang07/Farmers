package com.yedam.app.security;

import java.util.Map;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class OAuth2UserRequest {
	private final ClientRegistration clientRegistration;
	private final OAuth2AccessToken accessToken;
	private final Map<String, Object> additionalParameters;	//이건 여기서 안쓴다
}
