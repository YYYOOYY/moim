package org.edupoll.controller;

import java.util.List;

import org.edupoll.model.dto.response.MoimResponseData;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.Reply;
import org.edupoll.security.support.Account;
import org.edupoll.service.AttendanceService;
import org.edupoll.service.MoimService;
import org.edupoll.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class MoimController {

	@Autowired
	MoimService moimService;

	@Autowired
	ReplyService replyService;

	@Autowired
	AttendanceService attendanceService;

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
	public String MoimCreateTask(Moim moim, @AuthenticationPrincipal Account account) {

		moimService.create(moim, account.getUsername());

		return "redirect:/moim/post";
	}

	@GetMapping("moim/detail")
	public String gotoMoimDetail(@RequestParam String moimId, Model model, @RequestParam(defaultValue = "1") int p,
			@RequestParam(required = false) String error, HttpSession session, @AuthenticationPrincipal Account account,
			@RequestParam(required = false) String er) {
		MoimResponseData moim = moimService.findByPost(moimId);
		model.addAttribute("moim", moim);
		if (session.getAttribute("SPRING_SECURITY_CONTEXT") == null) {
			model.addAttribute("nonLogin", true);
			model.addAttribute("status", "참가 신청은 로그인이 필요합니다");
		} else {
			boolean rst = attendanceService.isJoinedAttendance(moimId, account.getUsername());
			if (rst) {
				model.addAttribute("joined", true);
				model.addAttribute("status", "모임참가취소");
			} else {
				model.addAttribute("joined", false);
				model.addAttribute("status", "모임참가신청");
			}
		}

		List<Reply> replys = replyService.findByAll(moimId, p);
		model.addAttribute("replys", replys);

		model.addAttribute("moimId", moimId);
		model.addAttribute("er", "r");
		if (error != null) {
			if (!error.isEmpty()) {
				model.addAttribute("error", "댓글 혹은 비밀번호를 입력해주세요");
			}
		}

		return "moim/detail";
	}

//	@PostMapping("moim/attendance")
//	public AttendanceJoinResponseData joinByMoim(@RequestParam String moimId, @SessionAttribute String logonId, Model model) {
//		
//		boolean rst = attendanceService.findByAttendance(moimId, logonId);
//		if(rst) {
//			attendanceService.deleteByAttendance(moimId, logonId);
//			
//			return null;
//		}else {
//			AttendanceJoinResponseData rstt = attendanceService.joinByMoim(moimId, logonId);		
//			
//			return rstt;
//		}
//		
//	}
}
