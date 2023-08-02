package org.edupoll.service;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.dto.response.MoimResponseData;
import org.edupoll.model.dto.response.UserResponseData;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.repository.FollowRepository;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserDetailRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailRepository userDetailRepository;

	@Autowired
	MoimRepository moimRepository;
	
	@Autowired
	FollowRepository followRepository;
	
	public List<UserResponseData> searchUser(String keyword, String logonId) {

		List<User> users = userRepository.findByNickContainingIgnoreCaseOrIdContainingIgnoreCase(keyword, keyword);
		
		List<UserResponseData> respList = new ArrayList<>();
		
		for(User user : users) {
			boolean rst = followRepository.existsByOwnerIdIsAndTargetIdIs(logonId, user.getId());
			UserResponseData urd = new UserResponseData(user);
			urd.setFollow(rst);
			respList.add(urd);
		}
		return respList;
	}
	
	public List<MoimResponseData> searchByMoims(String keyword) {
		
		List<Moim> moims = moimRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
		
		List<MoimResponseData> respList = new ArrayList<>();
		
		for(Moim moim : moims) {
			respList.add(new MoimResponseData(moim));
		}
		return respList;
	}
	
	public List<MoimResponseData> findByMoimPage(List<MoimResponseData> moims, int page) {
		int pageSize = 12;
		int startIndex = (page - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, moims.size());
		
		List<MoimResponseData> result = moims.subList(startIndex, endIndex);
		
		return result;
	}
	
	public List<String> page(List<MoimResponseData> moims) {
		long cnt = moims.size();
		List<String> pages = new ArrayList<>();
		for (int i = 1; i <= cnt / 12 + (cnt % 12 > 0 ? 1 : 0); i++) {
			pages.add(String.valueOf(i));
		}
		return pages;
	}
}
