package org.edupoll.model.dto.response;

import java.text.SimpleDateFormat;

import org.edupoll.model.entity.User;

public class UserResponseData {
	String id;
	String pass;
	String nick;
	String joinDay;
	String joinTime;
	Integer userDetailIdx;
	String avatarUrl;
	String description;
	
	boolean follow;
	
	public UserResponseData(User user) {
		this.id = user.getId();
		this.pass = user.getPass();
		this.nick = user.getNick();
		SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");		
		this.joinDay = dayFmt.format(user.getJoinDate());
		long diff = System.currentTimeMillis() - user.getJoinDate().getTime();
		this.joinTime = diff / (1000L*60*60*24) + "일 전";
		if(user.getUserDetail() != null) {
			description = user.getUserDetail().getDescription();
			if(user.getUserDetail().getAvatar() != null) {
				avatarUrl = user.getUserDetail().getAvatar().getUrl();				
			}
		}
	}

	public UserResponseData() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getJoinDay() {
		return joinDay;
	}

	public void setJoinDay(String joinDay) {
		this.joinDay = joinDay;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	public Integer getUserDetailIdx() {
		return userDetailIdx;
	}

	public void setUserDetailIdx(Integer userDetailIdx) {
		this.userDetailIdx = userDetailIdx;
	}

	public String getAvateaUrl() {
		return avatarUrl;
	}

	public void setAvateaUrl(String avateaUrl) {
		this.avatarUrl = avateaUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public boolean isFollow() {
		return follow;
	}

	public void setFollow(boolean follow) {
		this.follow = follow;
	}
	
}
