package com.yedam.app.security;

import java.util.Map;

public interface OAuth2UserInfo {
	Map<String, Object> getAttributes();
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String getNick();
    String getGender();
    String getProf();
}
