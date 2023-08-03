package com.yedam.app.security;

import java.util.Map;
import java.util.UUID;

public class NaverUserInfo implements OAuth2UserInfo {
	 	private Map<String, Object> attributes; //OAuth2User.getAttributes();
	    private Map<String, Object> attributesResponse;

	    public NaverUserInfo(Map<String, Object> attributes) {
	        this.attributes = (Map<String, Object>) attributes.get("response");
	        this.attributesResponse = (Map<String, Object>) attributes.get("response");
	    }
	    
	    @Override
	    public Map<String, Object> getAttributes() {
	        return attributes;
	    }

	    @Override
	    public String getProviderId() {
	        return attributesResponse.get("id").toString().substring(0, 22);
	    }

	    @Override
	    public String getProvider() {
	        return "Naver";
	    }

	    @Override
	    public String getEmail() {
	        return attributesResponse.get("email").toString();
	    }

	    @Override
	    public String getName() {
	    	if( attributesResponse.get("name")!= null) {
	    		return attributesResponse.get("name").toString();	    		
	    	}else {
	    		return attributesResponse.get("nickname").toString();
	    	}
	    }

		@Override
		public String getNick() {
			 return attributesResponse.get("nickname").toString();
		}

		@Override
		public String getGender() {
			String gen=attributesResponse.get("gender").toString();
			String gender=null;
			switch (gen) {
			case "F":
				gender="a1";
				break;
			case "M":
				gender="a0";
				break;
			default:
				gender=null;
				break;
			}
			return gender;
		}

		@Override
		public String getProf() {
			return attributesResponse.get("profile_image").toString();
		}
}
