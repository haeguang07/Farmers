package com.yedam.app.user.vo;

import lombok.Data;

@Data
public class OauthCodeVO {
	private String code;
	private String error;
	private String error_description;
	private String state;
}
