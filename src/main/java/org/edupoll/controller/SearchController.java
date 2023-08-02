package org.edupoll.controller;

import java.util.List;

import org.edupoll.model.dto.response.MoimResponseData;
import org.edupoll.model.dto.response.UserResponseData;
import org.edupoll.security.support.Account;
import org.edupoll.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

	@Autowired
	SearchService searchService;
	
	@GetMapping("/search")
	public String findByKeword(@RequestParam(defaultValue = "1") int page, @RequestParam(required = false) String q,
			@RequestParam(defaultValue = "모임검색") String keyword, 
			@AuthenticationPrincipal Account account, Model model) {
		if (keyword.equals("모임검색")) {
			if (q == null || q.equals("")) {
				return "search/form";
			} else {
				List<MoimResponseData> moims = searchService.searchByMoims(q);
				if (!moims.isEmpty()) {
					List<MoimResponseData> result = searchService.findByMoimPage(moims, page);
					model.addAttribute("moims", result);

					List<String> pages = searchService.page(moims);
					model.addAttribute("pages", pages);
				}else {
					model.addAttribute("emptyMoims", "\"" + q + "\"" + "로 검색한 모임정보가 없습니다.");
				}
				model.addAttribute("keyword", keyword);

				model.addAttribute("q", q);

				return "search/result";
			}
		} else {
			if (q == null || q.equals("")) {
				return "search/form";
			} else {
				List<UserResponseData> users = searchService.searchUser(q, account.getUsername());
				if (!users.isEmpty()) {
					model.addAttribute("users", users);
				}else {
					model.addAttribute("emptyUsers", "\"" + q + "\"" + "로 검색한 유저정보가 없습니다.");
				}
				if(account.getUsername() == null) {
					model.addAttribute("logon", false);
				}else {
					model.addAttribute("logon", true);
				}
				model.addAttribute("keyword", keyword);

				model.addAttribute("q", q);
				

				return "search/result";
			}
		}
	}
}
