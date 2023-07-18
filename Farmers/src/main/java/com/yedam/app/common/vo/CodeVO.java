package com.yedam.app.common.vo;

import lombok.Data;

@Data
public class CodeVO {
	// common_code_detail
	private String cmmnDetaCode;
	private String codeDesct;
	private String cmmnCodeNo;
	
	public CodeVO(String cmmnDetaCode) {
		super();
		this.cmmnDetaCode = cmmnDetaCode;
	}
	
}
