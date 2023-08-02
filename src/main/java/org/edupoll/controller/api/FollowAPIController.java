package org.edupoll.controller.api;

import org.edupoll.model.dto.response.FollowResponseData;
import org.edupoll.security.support.Account;
import org.edupoll.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FollowAPIController {
	
	@Autowired
	FollowService followService;
	
	@PostMapping("/follow/add")
	public FollowResponseData addFollow(@AuthenticationPrincipal Account account, String targetId) {
		System.out.println("팔로우 할 때 " + targetId);
		FollowResponseData rst = followService.addFollow(account.getUsername(), targetId);
		
		return rst;
	}
	
	@PostMapping("/follow/delete")
	public FollowResponseData unFollowing(@AuthenticationPrincipal Account account, String targetId) {
		System.out.println("언팔로우 할 때 " + targetId);
		return followService.unFollowing(account.getUsername(), targetId);
	}
}
