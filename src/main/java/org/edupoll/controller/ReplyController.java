package org.edupoll.controller;

import org.edupoll.model.dto.request.AddReplyRequestData;
import org.edupoll.model.dto.request.ReplyDeleteRequestData;
import org.edupoll.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	@PostMapping("reply/create")
	public String ReplyCreateTask(AddReplyRequestData data) {
	
		replyService.create(data);
		
		return "redirect:/moim/detail?moimId="+data.getMoimId();	
	}
	
	@GetMapping("reply/delete")
	public String ReplyDeleteTask(ReplyDeleteRequestData resp, Model model) {
		String[] a = resp.getMoimId().split(",");
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"+a[0]);
		if(resp.getPass().equals(resp.getReplyPass())) {
			replyService.delete(resp.getReplyId());
			
			return "redirect:/moim/detail?moimId="+a[0];
		}else {
			return "redirect:/moim/detail?moimId="+a[0]+"er=r";
		}
	}
}
