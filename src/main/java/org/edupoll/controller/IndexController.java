package org.edupoll.controller;

import org.edupoll.service.MoimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
	
	@Autowired
	MoimService moimService;
	
	@GetMapping("/")
	public String indexHandle(HttpSession session) {
		System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));
		return "index";
	}
	
	@GetMapping("/status")
	@ResponseBody
	public Object statusHandle(HttpSession session) {
		return session.getAttribute("SPRING_SECURITY_CONTEXT");
	}
}
