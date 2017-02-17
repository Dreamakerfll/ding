package com.dreamaker.controller.signature;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("signature")
public class SignatureController {
	
	@RequestMapping("getSignature")
	@ResponseBody
	public String getSignature(HttpServletRequest request){
		return null;
	}

}
