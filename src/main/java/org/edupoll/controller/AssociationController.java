package org.edupoll.controller;

import org.edupoll.model.entity.User;
import org.edupoll.model.entity.UserDetail;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssociationController {
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/assoc/01") 
	public String assoc01Handle(String userId) {
		
		User found = userRepository.findById(userId).orElse(null);
		System.out.println(found);
		
//		UserDetail detail =found.getUserDetail();
//		System.out.println(detail);
//		
		
		return "index";
	}
}
