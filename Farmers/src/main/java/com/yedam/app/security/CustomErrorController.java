package com.yedam.app.security;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class CustomErrorController implements ErrorController {
	private final String ERROR_PATH = "/error";

    @GetMapping(ERROR_PATH)
    public String redirectRoot(){
        return "user/admin/admin.html";
    }
    
    public String getErrorPath(){
        return ERROR_PATH;
    }
   
}
