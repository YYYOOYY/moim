package org.edupoll.controller;

import java.util.List;

import org.edupoll.model.dto.response.MoimResponseData;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.Reply;
import org.edupoll.service.AttendanceService2;
import org.edupoll.service.MoimService;
import org.edupoll.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


public class MoimController2 {

	@Autowired
	MoimService moimService;

	@Autowired
	ReplyService replyService;
	
	@Autowired
	AttendanceService2 attendanceService;
	
	@GetMapping("moim/post")
	public String findByPosts(@RequestParam(defaultValue = "1") int page, Model model) {
		List<MoimResponseData> posts = moimService.findByAll(page);
		model.addAttribute("posts", posts);

		List<String> pages = moimService.page();
		model.addAttribute("pages", pages);

		return "moim/post";
	}

	@GetMapping("moim/create")
	public String gotoMoimCreate() {

		return "moim/create";
	}

	@PostMapping("moim/create")
	public String MoimCreateTask(Moim moim, @SessionAttribute String logonId) {

		moimService.create(moim, logonId);

		return "redirect:/moim/post";
	}

	@GetMapping("moim/detail")
	public String gotoMoimDetail(@RequestParam String moimId, Model model, @RequestParam(defaultValue="1") int p,
			@RequestParam(required = false) String error, @SessionAttribute String logonId) {

		MoimResponseData moim = moimService.findByPost(moimId);
		model.addAttribute("moim", moim);
		
		boolean rst = attendanceService.findByAttendance(moimId, logonId);
		if(rst) {
			model.addAttribute("status", "모임참가취소");
		}else {
			model.addAttribute("status", "모임참가신청");
		}
		
		List<Reply> replys = replyService.findByAll(moimId, p);
		model.addAttribute("replys", replys);

		model.addAttribute("moimId", moimId);

		if (error != null) {
			if (!error.isEmpty()) {
				model.addAttribute("error", "댓글 혹은 비밀번호를 입력해주세요");
			}
		}

		return "moim/detail";
	}
	
	@GetMapping("moim/attendance")
	public String joinByMoim(@RequestParam String moimId, @SessionAttribute String logonId, Model model) {
		
		boolean rst = attendanceService.findByAttendance(moimId, logonId);
		if(rst) {
			model.addAttribute("status", "모임참가취소");
			attendanceService.deleteByAttendance(moimId, logonId);
			
			return "redirect:/moim/detail?moimId="+moimId;
		}else {
			model.addAttribute("status", "모임참가신청");
			
			boolean rstt = attendanceService.joinByMoim(moimId, logonId);		
			if(rstt) {
				return "redirect:/moim/detail?moimId="+moimId;							
			}else {
				return "redirect:/moim/detail?moimId="+moimId;
			}
		}
		
	}
}
