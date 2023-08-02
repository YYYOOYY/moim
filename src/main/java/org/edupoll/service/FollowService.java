package org.edupoll.service;

import java.util.Date;
import java.util.Optional;

import org.edupoll.model.dto.response.FollowResponseData;
import org.edupoll.model.entity.Follow;
import org.edupoll.model.entity.User;
import org.edupoll.repository.FollowRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
	
	@Autowired
	FollowRepository followRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public FollowResponseData addFollow(String ownerId, String targetId) {

		FollowResponseData result = new FollowResponseData();
		
		Optional<User> owner = userRepository.findById(ownerId);
		Optional<User> target = userRepository.findById(targetId);
		if(owner.isEmpty() || target.isEmpty()) {
			result.setResult(false);
			result.setMessage("오류가 발생했습니다(오류코드 - 01)");
			return result;
		}
		if(owner.get().getId().equals(target.get().getId())) {
			result.setResult(false);
			result.setMessage("자신을 팔로우 할 수 없습니다");
			return result;
		}
		if(followRepository.findByOwnerIdAndTargetId(ownerId, targetId) != null) {
			result.setResult(false);
			result.setMessage("오류가 발생했습니다(오류코드 - 02)");
			return result;
		}
		
		Follow follow = new Follow(owner.get(), target.get());
		follow.setCreated(new Date(System.currentTimeMillis()));
		followRepository.save(follow);
		
		FollowResponseData frd = new FollowResponseData();		
		
		frd.setResult(true);
		return frd;
	}
	
	public FollowResponseData unFollowing(String ownerId, String targetId) {
		boolean result = followRepository.existsByOwnerIdIsAndTargetIdIs(ownerId, targetId);
		FollowResponseData frd = new FollowResponseData();
		
		if(result) {
			Follow follow = followRepository.findByOwnerIdAndTargetId(ownerId, targetId);
			followRepository.delete(follow);
			frd.setResult(true);
			return frd;
		}
		frd.setResult(false);
		return frd;
	}
	
	public boolean findByFollowed(String ownerId, String targetId) {
		return followRepository.existsByOwnerIdIsAndTargetIdIs(ownerId, targetId);
	}
}
