package org.edupoll.controller;

import org.edupoll.model.dto.request.LoginRequestData;
import org.edupoll.model.dto.response.UserJoinData;
import org.edupoll.security.support.Account;
import org.edupoll.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserService userService;

	@PostMapping("/user/join")
	public String userJoinHandle(UserJoinData user, Model model) {
		
		boolean rst = userService.createNewUser(user);
		logger.debug("userJoinHandle's result : {} ", rst);
		if(rst) {
			return "redirect:/user/login?loginId="+user.getId();	
		}else {
			model.addAttribute("error", true);
			return "user/join";
		}
	}
	
	@GetMapping("/user/join")
	public String gotoJoin() {
		
		return "user/join";
	}
	
	
	public String userLoginHandle(LoginRequestData data, Model model) {
		boolean result = userService.isValidUser(data);
		logger.debug("userLoginHandle's result : {} ", result);
		if(result) {
			model.addAttribute("logonId", data.getLoginId());
			return "redirect:/";			
		}else {
			model.addAttribute("error", true);
			return "user/login";
		}
		
	}
	
	@GetMapping("/user/login")
	public String gotoLogin(LoginRequestData data, Model model) {
		model.addAttribute("data", data);
		
		return "user/login";
	}
	
	@GetMapping("/user/logout")
	public String userLogoutHandle(HttpSession session, Model model) {
		session.setAttribute("logonId", null);
		model.addAttribute("logonId", null);
//		sessionStatus.setComplete();
		
		return "redirect:/";
	}
	
	@GetMapping("/user/delete")
	public String deleteByUser(@AuthenticationPrincipal Account account) {

		userService.deleteSpecificUser(account.getUsername());
		
		return "redirect:/user/logout";
	}

	@GetMapping("/access/denied")
	public String showAccessDenied(Model model) {
		return "auth/access-denied";
	}

}
