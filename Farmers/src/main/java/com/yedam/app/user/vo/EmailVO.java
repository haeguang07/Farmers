package com.yedam.app.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailVO {
	  // 수신자
    private String to;
    
    // 제목
    private String subject;
    
    // 메시지
    private String message;
}
